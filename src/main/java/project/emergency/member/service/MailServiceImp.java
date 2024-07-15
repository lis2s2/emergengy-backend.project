package project.emergency.member.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import project.emergency.member.dto.MailDto;

@Service
public class MailServiceImp implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public String sendMail(MailDto mailDto) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        mimeMessageHelper.setFrom(mailDto.getSender());
        mimeMessageHelper.setTo(mailDto.getReceiver());
        mimeMessageHelper.setSubject(mailDto.getTitle());
        mimeMessageHelper.setText(mailDto.getMessage(), true);

        javaMailSender.send(mimeMessage);

        return "메일 전송 성공";
    }
}
