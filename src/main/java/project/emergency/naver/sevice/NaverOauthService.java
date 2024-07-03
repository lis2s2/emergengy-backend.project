//package project.emergency.naver.sevice;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class NaverOauthService {
//    private final OauthApiClient naverApiClient;
//
//    public OauthInfo getNaverInfo(NaverLoginRequest naverLoginRequest) {
//        String accessToken = naverApiClient.getOauthAccessToken(naverLoginRequest);
//        OauthProfileResponse oauthProfile = naverApiClient.getOauthProfile(accessToken);
//
//        return OauthInfo.builder()
//                .email(oauthProfile.getEmail())
//                .nickname(oauthProfile.getNickName())
//                .type(Member.Type.NAVER)
//                .authority(Member.Authority.ROLE_MEMBER)
//                .build();
//    }
//}
