package project.emergency.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import project.emergency.member.service.MemberService;
import project.emergency.member.service.MemberServiceImp;
import project.emergency.security.filter.ApiCheckFilter;
import project.emergency.security.filter.ApiLoginFilter;
import project.emergency.security.service.UserDetailsServiceImpl;
import project.emergency.security.util.JWTUtil;

@Configuration
// 스프링 시큐리티 필터(SecurityConfig)가 스프링 필터체인에 등록
@EnableWebSecurity
//secured 어노테이션 활성화, preAuthorize, postAuthorize 어노테이션 활성화
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    //  SecurityConfig 클래스에서 clientRegistrationRepository 빈을 주입 받도록 함
    private final ClientRegistrationRepository clientRegistrationRepository;

    private final DefaultOAuth2UserService oAuth2UserService;

    // 인증서비스
    @Bean
    UserDetailsService customUserDetailsService() {
        return new UserDetailsServiceImpl();
    }

    // 패스워드 암호화에 사용할 인코더
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // json 토큰 생성 클래스
    @Bean
    public JWTUtil jwtUtil() {
        return new JWTUtil();
    }

    // 사용자 관리 서비스
    @Bean
    public MemberService memberService() {
        return new MemberServiceImp();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 1.인증 필터 등록: /member 또는 /board 요청이 들어오면 사용자 인증 실행
        String[] arr = {"/member/*", "/freeboard/*", "/helpboard/*", "/shop/*", "/order/*", "/search/*", "/freecomment/*", "/helpcomment/*", "/logout"};
      
        http.addFilterBefore(new ApiCheckFilter(arr, jwtUtil(), customUserDetailsService()), UsernamePasswordAuthenticationFilter.class);

        // 2.권한 설정: 회원등록-아무나, 게시물-user, 회원-admin
        http
                .authorizeHttpRequests()
                .requestMatchers("/**").permitAll()

                .requestMatchers(
                        "/register", "/login/*", "/logout", "/login/oauth2/**", "/api/*", "/register/**", "/search/*"
                        ).permitAll()
                .requestMatchers("/order/*", "/mypage/**", "/freecomment/*", "/helpcomment/*", "/shop/*").hasAnyRole("USER", "ADMIN")

//                .requestMatchers("/helpboard/*").hasAnyRole("USER", "ADMIN")
//                .requestMatchers("/shop/*").hasAnyRole("USER", "ADMIN")
//                .requestMatchers("/order/*").hasAnyRole("USER", "ADMIN")
//                .requestMatchers("/register/*").hasAnyRole("USER", "ADMIN")
//                .requestMatchers("/mypage/*").hasAnyRole("USER", "ADMIN")
//                .requestMatchers("/login/*").hasAnyRole("USER", "ADMIN")
//                .requestMatchers("/search/*").hasAnyRole("USER", "ADMIN")
//                .requestMatchers("/freecomment/*").hasAnyRole("USER", "ADMIN")
//                .requestMatchers("/helpcomment/*").hasAnyRole("USER", "ADMIN")
//                .requestMatchers("/member/*").hasRole("ADMIN") // 회원 관리는 관리자이면 접근 가능
//                .anyRequest().authenticated()

                .and()
                // oauth2
                .oauth2Login(oauth2 -> oauth2
                        .redirectionEndpoint(endpoint -> endpoint.baseUri("/login/oauth2/**"))
                        .userInfoEndpoint(endpoint -> endpoint.userService(oAuth2UserService))
                )

                .csrf().disable() //csrf 비활성화
                //토큰을 사용하니까 세션은 사용안함
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler());

        // 3.로그인 필터 등록: 로그인 요청이 들어오면 토큰 발급
        // 인증매니저 생성
        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService()).passwordEncoder(passwordEncoder());
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        // 인증매니저 등록
        http.authenticationManager(authenticationManager);

        // 로그인 필터 생성: /api/login 요청이 들어오면 필터 실행
        ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/login", jwtUtil(), memberService());
        apiLoginFilter.setAuthenticationManager(authenticationManager);

        // Username~Filter: 사용자 이름과 비밀번호를 사용하는 시큐리티의 기본 필터
        // apiLoginFilter가 Username~Filter보다 먼저 실행되도록, 우선 순위를 설정
        http
                .addFilterBefore(apiLoginFilter, UsernamePasswordAuthenticationFilter.class)
                // 접근 권한이 없거나 인증에 실패했을 때 처리
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(authenticationEntryPoint());

        return http.build();
    }

    // 접근 권한이 없을 때 처리할 핸들러 생성
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {

        // 익명 클래스 생성
        AccessDeniedHandler handler = new AccessDeniedHandler () {

            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response,
                               AccessDeniedException accessDeniedException) throws IOException, ServletException {

                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setCharacterEncoding("utf-8");
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().write("권한이 없는 사용자입니다.");
            }
        };
        return handler;
    }

    // 인증에 실패했을 때 처리
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {

        AuthenticationEntryPoint handler = new AuthenticationEntryPoint() {

            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response,
                                 AuthenticationException authException) throws IOException, ServletException {

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setCharacterEncoding("utf-8");
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().write("인증되지 않은 사용자입니다.");
            }
        };
        return handler;
    }


    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return ((request, response, authentication) -> {
            DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) authentication.getPrincipal();

            String id = defaultOAuth2User.getAttributes().get("id").toString();
            String body = """
                    {"id":"%s"}
                    """.formatted(id);

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());

            PrintWriter writer = response.getWriter();
            writer.println(body);
            writer.flush();
        });
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {

        LogoutSuccessHandler handler = new LogoutSuccessHandler() {

            @Override
            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

                String token = request.getHeader("Authorization");
                if (token != null) {
                    jwtUtil().invalidateToken(token);
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.setContentType("text/html; charset=UTF-8");
                    response.getWriter().write("로그아웃에 성공했습니다. 더이상 해당 토큰을 사용할 수 없습니다.");
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.setContentType("text/html; charset=UTF-8");
                    response.getWriter().write("헤더에 토큰이 없어 로그아웃에 실패했습니다");
                }

            }
        };

        return handler;
    }
}

