package project.emergency.member.service;

import jakarta.mail.MessagingException;
import project.emergency.member.dto.MailDto;

public interface MailService {
    String sendMail(MailDto mailDto) throws MessagingException;
}
