package project.emergency.kakao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import project.emergency.member.service.MemberService;

import java.util.Map;

@RestController
@RequestMapping("/login/oauth2")
public class KakaoController {

    @PostMapping("/kakao")
    public ResponseEntity<?> kakaoLogin(@RequestBody Map<String, String> payload) {
        String code = payload.get("code");
//    public String getAccessToken(@RequestParam("code") String code) {
//        System.out.println("code = " + code);

        // 1. header 생성
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");

        // 2. body 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code"); //고정값
        params.add("client_id", "REST_API_KEY 입력");
        params.add("redirect_uri", "http://localhost:3000/oauth2/code/kakao"); //등록한 redirect uri
        params.add("code", code);

        // 3. header + body
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, httpHeaders);

        // 4. http 요청하기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                httpEntity,
                Object.class
        );

        System.out.println("response = " + response);

        return ResponseEntity.ok().body("로그인 성공");
    }
}

