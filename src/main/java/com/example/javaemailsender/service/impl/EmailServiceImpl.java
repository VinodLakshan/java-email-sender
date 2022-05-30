package com.example.javaemailsender.service.impl;

import com.example.javaemailsender.dto.EmailDto;
import com.example.javaemailsender.service.EmailService;
import org.springframework.stereotype.Service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(TemplateEngine templateEngine, JavaMailSender javaMailSender){
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public boolean sendEmail(EmailDto email) throws MessagingException {

        Context context = new Context();
        context.setVariable("email", email);

        String process = templateEngine.process("welcome", context);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        helper.setSubject("Hi Welcome!");
        helper.setText(process, true);
        helper.setTo(email.getTo());
        javaMailSender.send(mimeMessage);

        log.info("Welcome Email Sent");
        return true;
    }
}
