package org.example.service;

import org.example.enums.EmailTemplate;

import java.util.Map;

public interface ContactService {

    void sendEmailToEmployee(String userEmail,  Map<String,Object> data, EmailTemplate template);
}
