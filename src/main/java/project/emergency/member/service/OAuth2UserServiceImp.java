package project.emergency.member.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.openid.connect.sdk.assurance.claims.ISO3166_1Alpha2CountryCode;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import project.emergency.member.dto.MemberDTO;
import project.emergency.member.entitiy.Member;
import project.emergency.member.repository.MemberRepository;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImp extends DefaultOAuth2UserService {
// PrincipalOauth2UserService
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
            member = new MemberDTO("userId", "email@eamil.com");
        }

        if (oauthClientName.equals("naver")) {
            Map<String, String> responseMap = (Map<String, String>) oAuth2User.getAttributes().get("response");
        }

        return oAuth2User;
    }
}

