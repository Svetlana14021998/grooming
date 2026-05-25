package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.converter.ClientMessageConverter;
import org.example.dto.ClientMessageDto;
import org.example.model.ClientMessage;
import org.example.repository.ClientMessageRepository;
import org.example.service.ClientMessageService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientMessageServiceImpl implements ClientMessageService {

    private final ClientMessageRepository repository;

    private final ClientMessageConverter clientMessageConverter;

    @Override
    public void save(ClientMessageDto clientMessageDto) {
        ClientMessage clientMsg = clientMessageConverter.convertFromDtoToEntity(clientMessageDto);
        repository.save(clientMsg);
    }
}
