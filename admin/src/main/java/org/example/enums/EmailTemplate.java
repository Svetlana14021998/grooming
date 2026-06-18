package org.example.enums;

import lombok.Getter;

@Getter
public enum EmailTemplate {

    CONTACT_FORM("contact-form", "Сообщение от клиента с сайта"),
    RESET_PASSWORD("reset-password", "Сброс пароля");

    private final String templateName;

    private final String description;

    EmailTemplate(String templateName, String description) {
        this.templateName = templateName;
        this.description = description;
    }
}
