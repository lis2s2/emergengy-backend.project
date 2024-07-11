package project.emergency.kakao.controller;

import lombok.AllArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import project.emergency.kakao.service.KakaoUserService;
import project.emergency.member.entitiy.Member;

@AllArgsConstructor
@RestController
@RequestMapping("/api/proxy")
public class KakaoController {

    private final RestTemplate restTemplate;
    private final KakaoUserService kakaoUserService;

    @PostMapping("/kakao-token")
    public ResponseEntity<String> proxyKakaoToken(@RequestParam String code) {

        String TOKEN_URL = "https://kauth.kakao.com/oauth/token?grant_type=authorization_code"
                            + "&redirect_uri=http://localhost:3000/login/oauth2/code/kakao"
                            + "&client_id=6725e27a1c1047905dfd6bad61521355&code=" + code;

        HttpHeaders headers = new HttpHeaders();
        // 콘텐트 타입: application/x-www-form-urlencoded;charset=utf-8
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(TOKEN_URL, HttpMethod.POST, entity, String.class);
        return ResponseEntity.ok(response.getBody());
    }

    // 사용자 정보 가져오기
    // 헤더
    @GetMapping("/kakao-user")
    public ResponseEntity<String> getKakaoUserInfo(@RequestHeader("Authorization") String authorization) {

        String USER_INFO_URL = "https://kapi.kakao.com/v1/user/access_token_info";
//        String USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorization);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(USER_INFO_URL, HttpMethod.GET, entity, String.class);

        try {
            String responseBody = response.getBody();
            

            JSONObject userInfo = new JSONObject();
            JSONObject kakaoAccount = userInfo;
//            JSONObject profile = kakaoAccount.toJSONString();

            Member member = new Member();
            member.setMemId(userInfo.getAsString("id"));
            member.setMemEmail(kakaoAccount.getAsString("email"));
            member.setMemName(kakaoAccount.getAsString("nickname"));
            member.setProviderId(kakaoAccount.getAsString("id"));
            member.setProvider("카카오");
            member.setMemRole("ROLE_USER");
            member.setMemGrade("FAMILY");
            member.setMemPoint(0);

            kakaoUserService.saveKakaoUser(member);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error parsing JSON response");
        }

        return ResponseEntity.ok(response.getBody());
    }
}

// 다른 로직
//    public ResponseEntity<String> proxyKakaoLogin(@RequestBody Map<String, String> payload) {
//        String code = payload.get("code");
//    public String getAccessToken(@RequestParam("code") String code) {
//        System.out.println("code = " + code);
//
//        // 1. header 생성
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");
//
//        // 2. body 생성
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("grant_type", "authorization_code"); //고정값
//        params.add("client_id", "REST_API_KEY 입력");
//        params.add("redirect_uri", "http://localhost:3000/oauth2/code/kakao"); //등록한 redirect uri
//        params.add("code", code);
//
//        // 3. header + body
//        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, httpHeaders);
//
//        // 4. http 요청하기
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<Object> response = restTemplate.exchange(
//                "https://kauth.kakao.com/oauth/token",
//                HttpMethod.POST,
//                httpEntity,
//                Object.class
//        );
//
//        System.out.println("response = " + response);
//
//        return ResponseEntity.ok().body("로그인 성공");
//    }
//}

