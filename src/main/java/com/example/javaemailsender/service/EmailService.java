package com.example.javaemailsender.service;

import com.example.javaemailsender.dto.EmailDto;

import javax.mail.MessagingException;

public interface EmailService {

    boolean sendEmail(EmailDto email)  throws MessagingException;
}
