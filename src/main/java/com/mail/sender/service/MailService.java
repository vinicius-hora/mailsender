package com.mail.sender.service;

import com.mail.sender.configuration.MailConfiguration;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final MailConfiguration mailConfiguration;

    @Value("${microsoft.usermail}")
    private String emailMicrosoft;

    @Value("${google.usermail}")
    private String emailGoogle;

    @PostConstruct
    public void testeEnvio(){
//        this.envilarEmailMicrosoft();
        this.envilarEmailGoogle();
    }

    public void envilarEmailMicrosoft(){
        var mailMicrosoft = mailConfiguration.microsoftMail();


        try {
            String username = emailMicrosoft;

            MimeMessage message = mailMicrosoft.createMimeMessage();
            message.setFrom(new InternetAddress(username));


            MimeMessageHelper helper = new MimeMessageHelper(message, true, "ISO-8859-1");
            helper.setTo(emailMicrosoft);
            helper.setSubject("titulo");
            helper.setText("mensagem", true);


            mailMicrosoft.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public void envilarEmailGoogle(){
        var mailGoogle = mailConfiguration.googleMail();


        try {
            String username = emailMicrosoft;

            MimeMessage message = mailGoogle.createMimeMessage();
            message.setFrom(new InternetAddress(username));


            MimeMessageHelper helper = new MimeMessageHelper(message, true, "ISO-8859-1");
            helper.setTo("viniofer@gmail.com");
            helper.setSubject("titulo");
            helper.setText("mensagem", true);


            mailGoogle.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
