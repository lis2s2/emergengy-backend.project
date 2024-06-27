package project.emergency.security.dto;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import project.emergency.member.dto.MemberDTO;

import java.util.Arrays;

public class CustomUser extends User {

    public CustomUser(MemberDTO dto) {
        super(dto.getMemId(), dto.getMemPwd(), Arrays.asList(new SimpleGrantedAuthority(dto.getMemRole())));
//        super(dto.getMemId(), dto.getMemPwd(), dto.getMemName(), dto.getMemEmail(), dto.getMemNick(), Arrays.asList((new SimpleGrantedAuthority(dto.getMemGrade()))));
    }
}
