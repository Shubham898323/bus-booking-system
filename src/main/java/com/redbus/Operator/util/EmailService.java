package com.redbus.Operator.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendEmailWithAttachment(String toEmail, String subject, String body, byte[] attachment, String attachmentName) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("shubhammmahure@gmail.com", "shubham");
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(body, true); // Set to true for HTML content

        // Attach the PDF to the email
        helper.addAttachment(attachmentName + ".pdf", () -> new ByteArrayInputStream(attachment), "application/pdf");

        // Send the email
        emailSender.send(message);
    }
}
