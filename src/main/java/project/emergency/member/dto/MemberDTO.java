package project.emergency.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {

    @NotBlank(message = "아이디를 입력해주세요.")
    String memId; // 아이디

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)[a-zA-Z\\d]{6,15}$")
    String memPwd; // 패스워드

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식이 아닙니다..")
    String memEmail; // 이메일

    @NotBlank(message = "이름을 입력해주세요.")
    @Size(min = 2, max = 8, message = "이름을 2~8자 사이로 입력해주세요.")
    String memName; // 이름

    String provider;

    String providerId;

//    String memNick; // 닉네임

    String memGrade; // 등급

    String memRole; // 권한

    // 기본 포인트가 0이 되게끔 하는 어노테이션
    @Builder.Default
    int memPoint = 0; // 포인트

    LocalDateTime regDate; //등록일

    LocalDateTime modDate; //수정일

    public MemberDTO(String memId, String memPwd, String memName, String memEmail) {
        this.memId = getMemId();
        this.memPwd = getMemPwd();
        this.memEmail = getMemEmail();
        this.memName = getMemName();
        this.memGrade = getMemGrade();
        this.memRole = getMemRole();
        this.memPoint = getMemPoint();
    }
}
