package com.mail.sender.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@Slf4j
public class MailConfiguration {
    @Value("${microsoft.usermail}")
    private String emailMicrosoft;
    @Value("${microsoft.password}")
    private String passwordMicrosoft;
    @Value("${microsoft.mail.host}")
    private String microsoftHost;
    @Value("${microsoft.mail.auth}")
    private String microsoftAuth;
    @Value("${microsoft.mail.port}")
    private String microsoftPort;
    @Value("${microsoft.mail.starttls}")
    private String microsoftStarttls;
    @Value("${microsoft.mail.smtp.socketFactory.fallback}")
    private String microsoftSocketFactory;
    @Value("${microsft.enable:}")
    private boolean microsoftEnabe;


    //GOOGLE VARIABLES
    @Value("${google.usermail}")
    private String emailGoogle;
    @Value("${google.password}")
    private String passwordGoogle;
    @Value("${google.mail.host}")
    private String googleHost;
    @Value("${google.mail.auth}")
    private String googleAuth;
    @Value("${google.mail.host.port}")
    private String googlePort;
    @Value("${google.mail.host.starttls}")
    private String googleStarttls;
    @Value("${google.mail.smtp.socketFactory.fallback}")
    private String googleSocketFactory;
    @Value("${google.enable:}")
    private boolean googleEnable;

    private JavaMailSender Build(String userEmail, String type){

        String token = accesToken();

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);

        mailSender.setUsername(username);
        mailSender.setPassword(token);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", starttls);
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.auth.mechanisms", "XOAUTH2");
        props.put("mail.smtp.ssl.trust", host);

        Session session = Session.getInstance(props);
        mailSender.setSession(session);

        return mailSender;

    }

    private Properties microsoftConfiguration(JavaMailSenderImpl mailSender){
        Properties props = mailSender.getJavaMailProperties();

        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", microsoftAuth);
        props.put("mail.smtp.starttls.enable", microsoftStarttls);
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.auth.mechanisms", "PLAIN");
        props.put("mail.smtp.ssl.trust", microsoftHost);

        return props;

    }

    private Properties googleConfiguration(JavaMailSenderImpl mailSender){
        Properties props = mailSender.getJavaMailProperties();

        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", googleAuth);
        props.put("mail.smtp.starttls.enable", googleStarttls);
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.auth.mechanisms", "PLAIN");
        props.put("mail.smtp.ssl.trust", googleHost);

        return props;

    }
}
