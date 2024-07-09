package project.emergency.naver.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/proxy")
public class NaverController {

    private final RestTemplate restTemplate;

    public NaverController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/naver-token")
    public ResponseEntity<String> proxyNaverToken(@RequestParam String code, @RequestParam String state) {
        String TOKEN_URL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code"
                + "&client_id=YOUR_CLIENT_ID&client_secret=YOUR_CLIENT_SECRET&code=" + code + "&state=" + state;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(TOKEN_URL, HttpMethod.POST, entity, String.class);
        return ResponseEntity.ok(response.getBody());
    }
}
