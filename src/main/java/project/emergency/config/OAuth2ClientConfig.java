//package project.emergency.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.oauth2.client.registration.ClientRegistration;
//import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
//import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//
//public class OAuth2ClientConfig {
//
//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        ClientRegistration registration = ClientRegistration.withRegistrationId("kakao")
//                .clientId("your-client-id")
//                .clientSecret("your-client-secret")
//                .scope("openid", "profile", "email")
//                .authorizationUri("https://accounts.kakao.com/o/oauth2/auth")
//                .tokenUri("https://oauth2.googleapis.com/token")
//                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
//                .userNameAttributeName("sub")
//                .clientName("Google")
//                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .build();
//
//        return new InMemoryClientRegistrationRepository(registration);
//    }
//}
