package project.emergency.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import project.emergency.member.dto.MemberDTO;
import project.emergency.member.service.MemberService;
import project.emergency.security.dto.CustomUser;

/*
 * 사용자 커스텀 로그인 인증 서비스
 * loadUserByUsername를 오버라이드 하여 사용자 정보를 조회하고 인증객체를 생성한다
 * 유저 인증서비스는 provider에 등록한다
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MemberService service;

    // 사용자 아이디를 기반으로 인증객체를 생성하는 메소드
    @Override
    public UserDetails loadUserByUsername(String userName) {

        System.out.println("login id : " + userName);

        // 아이디로 실제 회원정보 가져오기
        MemberDTO dto = service.readId(userName);

        if(dto == null) {
            throw new UsernameNotFoundException(""); //사용자 정보가 없다면 에러를 발생시킴
        } else {
            return new CustomUser(dto); //dto를 인증객체로 변환하여 반환
        }

    }

}
