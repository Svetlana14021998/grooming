package org.example.schedules;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.converter.ClientMessageConverter;
import org.example.exception.SendMessagingException;
import org.example.model.ClientMessage;
import org.example.repository.ClientMessageRepository;
import org.example.service.ContactService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class EmailIncorrectService {

    private final ClientMessageRepository clientMessageRepository;

    private final ContactService contactService;

    private final ClientMessageConverter converter;

    @Scheduled(cron = "0 0 12,16 * * *")
    @Transactional
    public void retrySentFailedEmails() {
        log.info("run scheduler for send error messages to admin");
        LocalDateTime expiryDate = LocalDateTime.now().minusDays(1);
        List<ClientMessage> msg = clientMessageRepository.findByEmailSentFalseAndEmailSentAtAfter(expiryDate);
        log.info("find {} messages for send", msg.size());
        for (ClientMessage m : msg) {
            try {
                contactService.sendEmailToAdmin(converter.convertFromEntityToDto(m));
                log.info("Success send email with id: {}", m.getId());
            } catch (SendMessagingException e) {
                log.error("Can't send email with id: {}", m.getId());
            }
        }
        log.info("finish scheduler for send error messages to admin");
    }
}
