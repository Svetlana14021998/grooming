package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.config.AppConfig;
import org.example.dto.ClientMessageDto;
import org.example.entity.EmailRequest;
import org.example.exception.SendMessagingException;
import org.example.service.ClientMessageService;
import org.example.service.ContactService;
import org.example.service.EmailService;
import org.springframework.stereotype.Service;

import static org.example.enums.EmailTemplate.CONTACT_FORM;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final EmailService emailService;

    private final AppConfig config;

    private final ClientMessageService clientMessageService;

    @Override
    public void sendEmailToAdmin(ClientMessageDto msg) {
        var savedMsg = clientMessageService.save(msg);

        try {
            EmailRequest adminEmail = EmailRequest.builder()
                .to(config.getAdminEmail())
                .subject("Новое сообщение с сайта от пользователя")
                .template(CONTACT_FORM)
                .data(savedMsg)
                .build();
            emailService.sendEmail(adminEmail);
            savedMsg.setEmailSent(true);
            savedMsg.setEmailError(null);
            clientMessageService.save(savedMsg);
        } catch (SendMessagingException e) {
            savedMsg.setEmailError("Can`t send msg type = %s by schedule".formatted(CONTACT_FORM.getDescription()));
            clientMessageService.save(savedMsg);
            throw new SendMessagingException(e.getMessage());
        }
    }
}
