package project.emergency.naver.controller;

import net.minidev.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import project.emergency.member.entitiy.Member;
import project.emergency.naver.service.NaverUserService;

@RestController
@RequestMapping("/api/proxy")
public class NaverController {

    private final RestTemplate restTemplate;

    private final NaverUserService naverUserService;

    public NaverController(RestTemplate restTemplate, NaverUserService naverUserService) {
        this.restTemplate = restTemplate;
        this.naverUserService = naverUserService;
    }

    // 토큰 받아오기
    // 파람 + 헤더 json
    @PostMapping("/naver-token")
    public ResponseEntity<String> proxyNaverToken(@RequestParam String code, @RequestParam String state) {

        String TOKEN_URL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code"
                            + "&client_id=QiZW7Xq40T2iOCfUC6EH&client_secret=nImi9vDd6q&code=" + code
                            + "&state=" + state;

        HttpHeaders headers = new HttpHeaders();
        // 콘텐트 타입: application/json
        headers.add("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(TOKEN_URL, HttpMethod.POST, entity, String.class);
        return ResponseEntity.ok(response.getBody());
    }

    // 사용자 정보 가져오기
    // 헤더
    @GetMapping("/naver-user")
    public ResponseEntity<String> getNaverUserInfo(@RequestHeader("Authorization") String authorization) {

        String USER_INFO_URL = "https://openapi.naver.com/v1/nid/me";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorization);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(USER_INFO_URL, HttpMethod.GET, entity, String.class);

        try {
            String responseBody = response.getBody();

            // 응답 본문을 JSONObject로 변환
            JSONObject userInfo = new JSONObject();
            JSONObject responseJson = userInfo;

            Member member = new Member();
            member.setMemId(responseJson.getAsString("id"));
            member.setMemEmail(responseJson.getAsString("email"));
            member.setMemName(responseJson.getAsString("name"));
            member.setProviderId(responseJson.getAsString("id"));
            member.setProvider("네이버");
            member.setMemRole("ROLE_USER");
            member.setMemGrade("FAMILY");
            member.setMemPoint(0);

            naverUserService.saveNaverUser(member);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(response.getBody());
    }


}
