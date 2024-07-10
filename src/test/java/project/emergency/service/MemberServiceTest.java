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
                .memId("lees6895@naver.com")
                .memPwd("kgkg1237")
                .memEmail("lees6895@naver.com")
                .memName("이수현")
//                .memNick("똥쟁이")
                .memGrade("Regular") // Regular 또는 Gold
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
    public void 회원아이디단건조회() {
        MemberDTO dto = service.readId("user1");
        System.out.println(dto);
    }

    @Test
    public void 회원이메일단건조회() {
        MemberDTO dto = service.readEmail("user1@naver.com");
        System.out.println(dto);
    }

    @Test
    public void 아이디찾기() {
        String dto = service.findid("이수현", "hyeon6895@naver.com");
        System.out.println(dto);
    }

    @Test
    public void 회원정보수정() {
        MemberDTO dto = service.readEmail("hyeon1221@naver.com");
        dto.setMemEmail("hyeon0000@naver.com");
        service.modifyMember(dto.getMemId(), dto.getMemEmail(), dto.getMemPwd());
        System.out.println(dto);
    }
}
