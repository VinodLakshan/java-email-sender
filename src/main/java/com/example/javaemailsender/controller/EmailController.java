package com.example.javaemailsender.controller;

import com.example.javaemailsender.dto.EmailDto;
import com.example.javaemailsender.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/email")
@Slf4j
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/send")
    public ResponseEntity sendEmail(){

        EmailDto emailDto = new EmailDto();
        emailDto.setTo("Any email you want to send email to");
        emailDto.setToName("David");

        try {
            boolean isSent = emailService.sendEmail(emailDto);

            if(isSent) {
                return ResponseEntity.ok("Email Sent");

            } else {
                return new ResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (MessagingException e) {

            log.error(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
