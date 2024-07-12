package project.emergency.member.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailDto {

    private String sender;
    private String receiver;
    private String title;
    private String message;

//    public void setSender(String mail) {
//    }
//
//    public void setTitle(String 나지금급해_홈페이지_임시비밀번호_안내) {
//    }
//
//    public void setMessage(String s) {
//    }
//
//    public void setReceiver(String memEmail) {
//    }
//
//    public String getReceiver() {
//        return "";
//    }
//
//    public String getTitle() {
//        return "";
//    }
//
//    public String getMessage() {
//        return "";
//    }
}
