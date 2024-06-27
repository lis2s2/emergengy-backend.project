package project.emergency.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.emergency.member.dto.MemberDTO;
import project.emergency.member.service.MemberService;

import java.util.List;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberService service;

    @Test
    public void 회원등록() {
        MemberDTO dto = MemberDTO.builder()
                .memId("user6")
                .memPwd("1234")
                .memEmail("user1@naver.com")
                .memName("유저1")
                .memNick("똥쟁이")
//                .memGrade("Regular") // Regular 또는 Gold
                .memRole("Role_USER")
                .memPoint(30000)
                .build();

        boolean isSuccess = service.register(dto);
        if(isSuccess) {
            System.out.println("회원이 등록되었습니다.");
        }else {
            System.out.println("이미 등록된 회원입니다.");
        }
    }

    @Test
    public void 회원목록조회() {
        List<MemberDTO> list = service.getList();
        for(MemberDTO dto : list) {
            System.out.println(dto);
        }
    }

    @Test
    public void 회원단건조회() {
        MemberDTO dto = service.read("user1");
        System.out.println(dto);
    }
}
