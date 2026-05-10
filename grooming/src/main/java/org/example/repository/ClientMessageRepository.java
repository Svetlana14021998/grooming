package org.example.repository;

import org.example.model.ClientMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ClientMessageRepository extends JpaRepository<ClientMessage,Long> {

    List<ClientMessage> findByEmailSentFalseAndEmailSentAtAfter(LocalDateTime expirationDate);
}
