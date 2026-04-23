package org.example.service;

import org.example.dto.ClientMessageDto;
import org.example.model.ClientMessage;

public interface ClientMessageService {

    ClientMessage save(ClientMessageDto clientMessageDto);
}
