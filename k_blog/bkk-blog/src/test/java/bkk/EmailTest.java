package bkk;

import com.bkk.BkkBlogApplication;
import com.bkk.service.EmailService;
import com.bkk.service.impl.EmailServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

@SpringBootTest(classes = BkkBlogApplication.class)
public class EmailTest {

    @Test
    public void emailTest() {
        EmailService emailService = new EmailServiceImpl();
        try {
            emailService.sendCode("3152013378@qq.com");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
