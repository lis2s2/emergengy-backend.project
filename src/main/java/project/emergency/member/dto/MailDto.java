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
}
