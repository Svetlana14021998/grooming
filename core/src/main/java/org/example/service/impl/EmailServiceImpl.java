package org.example.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.example.config.AppConfig;
import org.example.entity.EmailRequest;
import org.example.exception.SendMessagingException;
import org.example.service.EmailService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    private final SpringTemplateEngine templateEngine;

    private final AppConfig config;

    public void sendEmail(EmailRequest emailRequest) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());
            helper.setFrom(config.getAutoSendEmail());
            helper.setText(buildEmailContent(emailRequest), true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new SendMessagingException("Ошибка при отправке письма: " + e.getMessage());
        }
    }

    private String buildEmailContent(EmailRequest request) {
        Context context = new Context();
        context.setVariable("data", request.getData());

        return templateEngine.process(config.getMailPrintFormPath() + request.getTemplate().getTemplateName(), context);
    }
}
