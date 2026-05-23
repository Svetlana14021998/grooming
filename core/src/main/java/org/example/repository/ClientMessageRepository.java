package org.example.repository;

import org.example.model.ClientMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientMessageRepository extends JpaRepository<ClientMessage, Long> {

}
