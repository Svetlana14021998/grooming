package org.example.converter;

import org.example.dto.ClientMessageDto;
import org.example.model.ClientMessage;
import org.springframework.stereotype.Component;

@Component
public class ClientMessageDtoToClientMessageConverter {

    public ClientMessage convert(ClientMessageDto clientMessageDto){
        return ClientMessage.builder()
            .name(clientMessageDto.getName())
            .email(clientMessageDto.getEmail())
            .phone(clientMessageDto.getPhone())
            .message(clientMessageDto.getMessage())
            .emailSent(clientMessageDto.isEmailSent())
            .emailError(clientMessageDto.getEmailError())
            .build();
    }
}
