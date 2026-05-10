package org.example.service;

import org.example.dto.ClientMessageDto;

public interface ContactService {

    void sendEmailToAdmin(ClientMessageDto msg);
}
