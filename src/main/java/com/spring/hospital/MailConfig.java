package com.spring.hospital;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import io.github.cdimascio.dotenv.Dotenv;
import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender getJavaMailSender() {
        Dotenv dotenv = Dotenv.load();
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(dotenv.get("SMTP_HOST"));
        mailSender.setPort(Integer.parseInt(dotenv.get("SMTP_PORT")));
        mailSender.setUsername(dotenv.get("SMTP_USERNAME"));
        mailSender.setPassword(dotenv.get("SMTP_PASSWORD"));
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.class", "com.sun.mail.smtp.SMTPTransport");
        return mailSender;
    }

}