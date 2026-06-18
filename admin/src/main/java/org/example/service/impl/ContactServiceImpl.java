package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.entity.EmailRequest;
import org.example.enums.EmailTemplate;
import org.example.exception.SendMessagingException;
import org.example.service.ContactService;
import org.example.service.EmailService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final EmailService emailService;

    @Override
    public void sendEmailToEmployee(String userEmail, Map<String, Object> data, EmailTemplate template) {
        try {
            EmailRequest adminEmail = EmailRequest.builder()
                .to(userEmail)
                .subject(template.getDescription())
                .template(template)
                .data(data)
                .build();
            emailService.sendEmail(adminEmail);
        } catch (SendMessagingException e) {
            throw new SendMessagingException(e.getMessage());
        }
    }
}
