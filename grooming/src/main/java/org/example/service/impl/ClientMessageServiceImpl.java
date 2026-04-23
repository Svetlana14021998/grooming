package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.converter.ClientMessageDtoToClientMessageConverter;
import org.example.dto.ClientMessageDto;
import org.example.model.ClientMessage;
import org.example.repository.ClientMessageRepository;
import org.example.service.ClientMessageService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientMessageServiceImpl implements ClientMessageService {

    private final ClientMessageRepository repository;

    private final ClientMessageDtoToClientMessageConverter clientMessageConverter;

    @Override
    public ClientMessage save(ClientMessageDto clientMessageDto) {
        ClientMessage clientMsg = clientMessageConverter.convert(clientMessageDto);
        return repository.save(clientMsg);
    }
}
