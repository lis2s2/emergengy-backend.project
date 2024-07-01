package project.emergency.member.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {

    String memId; // 아이디

    String memPwd; // 패스워드

    String memEmail; // 이메일

    String memName; // 이름

//    String memNick; // 닉네임

    String memGrade; // 등급

    String memRole; // 권한

    // 기본 포인트가 0이 되게끔 하는 어노테이션
    @Builder.Default
    int memPoint = 0; // 포인트

    LocalDateTime regDate; //등록일

    LocalDateTime modDate; //수정일

    // 등급 업데이트 메소드
    public void updateGrade() {
        if (memPoint >= 30000) {
            setMemGrade("Gold");
        } else {
            setMemGrade("Regular");
        }
    }
}
