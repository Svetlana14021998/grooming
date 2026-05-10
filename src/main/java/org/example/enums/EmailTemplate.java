package org.example.enums;

import lombok.Getter;

@Getter
public enum EmailTemplate {

    CONTACT_FORM("contact-form","Сообщение от клиента с сайта")
  ;

    private final String templateName;

    private final String description;

    EmailTemplate(String templateName, String description) {
        this.templateName = templateName;
        this.description = description;
    }
}
