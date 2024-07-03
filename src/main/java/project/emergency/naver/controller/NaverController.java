//package project.emergency.naver.controller;
//
//import com.nimbusds.oauth2.sdk.TokenResponse;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import project.emergency.naver.DTO.NaverLoginRequest;
//
//@Controller
//@RequestMapping("login/oauth2/code")
//public class NaverController {
//
//    @PostMapping("/naver")
//    public ResponseEntity<TokenResponse> naverLogin(@RequestBody NaverLoginRequest naverLoginRequest) {
//        OauthInfo naverInfo = naverOauthService.getNaverInfo(naverLoginRequest);
//
//        TokenResponse tokenResponse = oauthMemberService.getAccessTokenWithOauthInfo(naverInfo);
//
//        return ResponseEntity.ok(tokenResponse);
//    }
//}
