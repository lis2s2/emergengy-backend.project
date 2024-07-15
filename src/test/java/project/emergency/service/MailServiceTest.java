package project.emergency.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import project.emergency.member.dto.MailDto;
import project.emergency.member.service.MailServiceImp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MailServiceTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private MailServiceImp mailService;

    public MailServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendMail() throws MessagingException {
        // Arrange
//        MailDto mailDto = new MailDto();
//        mailDto.setSender("hyeon6895@naver.com");
//        mailDto.setReceiver("test@example.com");
//        mailDto.setTitle("Test Mail");
//        mailDto.setMessage("This is a test email.");
//
//        MimeMessage mimeMessage = mock(MimeMessage.class);
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
//
//        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
//        doNothing().when(javaMailSender).send(mimeMessage);
//
//        // Act
//        String result = mailService.sendMail(mailDto);
//
//        // Assert
//        verify(javaMailSender, times(1)).createMimeMessage();
//        verify(javaMailSender, times(1)).send(mimeMessage);
//        assertEquals("메일 전송 성공", result);
    }
}
