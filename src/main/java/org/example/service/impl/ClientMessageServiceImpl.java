package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.converter.ClientMessageConverter;
import org.example.dto.ClientMessageDto;
import org.example.exception.EntityNotFoundException;
import org.example.model.ClientMessage;
import org.example.repository.ClientMessageRepository;
import org.example.service.ClientMessageService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientMessageServiceImpl implements ClientMessageService {

    private final ClientMessageRepository repository;

    private final ClientMessageConverter clientMessageConverter;

    @Override
    public ClientMessageDto save(ClientMessageDto clientMessageDto) {
        ClientMessage clientMsg = clientMessageConverter.convertFromDtoToEntity(clientMessageDto);
        if (clientMsg.getId() == null) {
            return clientMessageConverter.convertFromEntityToDto(repository.save(clientMsg));
        }
        ClientMessage fromDB =
            repository.findById(clientMsg.getId())
                .orElseThrow(() -> new EntityNotFoundException("Email with id=%s not found".formatted(clientMsg.getId())));
        fromDB.setEmailError(clientMsg.getEmailError());
        fromDB.setEmailSent(clientMessageDto.isEmailSent());
        return clientMessageConverter.convertFromEntityToDto(repository.save(fromDB));
    }
}
