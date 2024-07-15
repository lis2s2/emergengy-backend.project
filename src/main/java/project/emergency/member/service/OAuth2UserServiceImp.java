package project.emergency.member.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import project.emergency.member.dto.MemberDTO;
import project.emergency.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImp extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(request);

        String oauthClientName = request.getClientRegistration().getClientName();

        try {
            System.out.println(new ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        MemberDTO member = null;
        String userId = null;


        if (oauthClientName.equals("kakao")) {
            userId = "kakao" + oAuth2User.getAttributes().get("id");
//            member = new MemberDTO("userId", "email@eamil.com");
        }

        if (oauthClientName.equals("naver")) {
//            Map<String, String> responseMap = (Map<String, String>) oAuth2User.getAttributes().get("response");
        }

        return oAuth2User;
    }
}

