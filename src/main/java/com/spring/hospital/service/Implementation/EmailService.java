package com.spring.hospital.service.Implementation;

import com.spring.hospital.entity.Email;
import com.spring.hospital.service.IEmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.core.env.Environment;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService implements IEmailService {


    @Autowired
    private final JavaMailSender javaMailSender;


    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;

    }


    @Override
    public void sendEmail(Email email) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        try {
            helper.setFrom(email.getSender());
            helper.setTo(email.getRecipient());
            helper.setSubject(email.getSubject());
            helper.setText(email.getBody()+"Click this Link for More information"+ email.getURL(),true);
            javaMailSender.send(message);

        } catch (MessagingException e) {
            throw new MailParseException(e);
        }
    }
}
