package org.example.converter;

import org.example.dto.ClientMessageDto;
import org.example.model.ClientMessage;
import org.springframework.stereotype.Component;

@Component
public class ClientMessageConverter {

    public ClientMessage convertFromDtoToEntity(ClientMessageDto clientMessageDto) {
        return ClientMessage.builder()
            .id(clientMessageDto.getId())
            .name(clientMessageDto.getName())
            .email(clientMessageDto.getEmail())
            .phone(clientMessageDto.getPhone())
            .message(clientMessageDto.getMessage())
            .emailSent(clientMessageDto.isEmailSent())
            .emailError(clientMessageDto.getEmailError())
            .build();
    }

    public ClientMessageDto convertFromEntityToDto(ClientMessage clientMessage) {
        return ClientMessageDto.builder()
            .id(clientMessage.getId())
            .name(clientMessage.getName())
            .email(clientMessage.getEmail())
            .phone(clientMessage.getPhone())
            .message(clientMessage.getMessage())
            .emailSent(clientMessage.isEmailSent())
            .emailError(clientMessage.getEmailError())
            .build();
    }
}
