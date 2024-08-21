package project.emergency.member.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import project.emergency.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImp extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {

        System.out.println("--------------------------------");
        System.out.println("userRequest: " + request);

        String oauthClientName = request.getClientRegistration().getClientName();
        System.out.println(oauthClientName);

        OAuth2User oAuth2User = super.loadUser(request);
        oAuth2User.getAttributes().forEach((k,v) -> {
            System.out.println(k + ":" + v);
        });

        try {
            System.out.println(new ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));
        } catch (Exception e) {
            e.printStackTrace();
        }

//        MemberDTO member = null;
        String email = null;

        if (oauthClientName.equals("kakao")) {
            email = "kakao" + oAuth2User.getAttributes().get("id");
        }

        if (oauthClientName.equals("naver")) {
            email = "naver" + oAuth2User.getAttributes().get("id");
        }

        if (oauthClientName.equals("google")) {
            email = "google" + oAuth2User.getAttributes().get("id");
        }

        // 자동으로 회원가입
//        MemberDTO memberDTO = MemberService.saveSocialMember(memEmail);

//        return new CustomUser(memberDTO);
        return oAuth2User;
    }
}

