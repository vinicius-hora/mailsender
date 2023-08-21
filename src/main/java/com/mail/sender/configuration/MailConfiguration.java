package com.mail.sender.configuration;

import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
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
    @Value("${google.mail.port}")
    private String googlePort;
    @Value("${google.mail.starttls}")
    private String googleStarttls;
    @Value("${google.mail.socketFactory}")
    private String googleSocketFactory;
    @Value("${google.enable:}")
    private boolean googleEnable;

    public JavaMailSender googleMail(){
        return googletBuild("teste");
    }

    public JavaMailSender microsoftMail(){
        return microsoftBuild("teste");
    }

    private JavaMailSender microsoftBuild(String autenticationType){

//        String token = accesToken();

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(microsoftHost);
        mailSender.setPort(Integer.parseInt(microsoftPort));

        mailSender.setUsername(emailMicrosoft);
        mailSender.setPassword(passwordMicrosoft);

        Properties props = this.microsoftConfiguration(mailSender);

        Session session = Session.getInstance(props);
        mailSender.setSession(session);
        session.setDebug(true);

        return mailSender;

    }



    private Properties microsoftConfiguration(JavaMailSenderImpl mailSender){
        Properties props = mailSender.getJavaMailProperties();

        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", microsoftAuth);
        props.put("mail.smtp.starttls.enable", microsoftStarttls);
        props.put("mail.smtp.starttls.required", "true");
//        props.put("mail.smtp.auth.mechanisms", "PLAIN");
        props.put("mail.smtp.ssl.trust", microsoftHost);
        props.put("mail.smtp.auth.login.disable", "false");
        props.put("mail.smtp.auth.plain.disable", "false");
        props.put("mail.smtp.auth.xoauth2.disable", "true");
        props.put("mail.debug.auth", "true");
        props.put("mail.debug.auth.password", "true");

        return props;

    }

    private JavaMailSender googletBuild(String autenticationType){

//        String token = accesToken();

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(googleHost);
        mailSender.setPort(Integer.parseInt(googlePort));

        mailSender.setUsername(emailGoogle);
        mailSender.setPassword(passwordGoogle);

        Properties props = this.microsoftConfiguration(mailSender);

        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailGoogle, passwordGoogle);
                    }
                });
        session.setDebug(true);

        return mailSender;

    }

    private Properties googleConfiguration(JavaMailSenderImpl mailSender){
        Properties props = mailSender.getJavaMailProperties();

        props.put("mail.smtp.auth", googleAuth);
        props.put ("mail.smtp.ssl.enable", "true");
//        props.put("mail.smtp.starttls.enable", googleStarttls);
        props.put("mail.smtp.starttls.required", "true");
//        props.put("mail.smtp.auth.mechanisms", "PLAIN");
//        props.put("mail.smtp.ssl.trust", googleHost);
        props.put("mail.smtp.host", googleHost);
        props.put("mail.smtp.port", googlePort);
        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.socketFactory.class", googleSocketFactory);
        props.put("mail.smtp.starttls.enable", googleStarttls);


        return props;

    }
}
