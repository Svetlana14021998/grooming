package org.example.enums.task;

import lombok.Getter;

@Getter
public enum LinkedEntityType {
    FEEDBACK("Обратная связь"),
    USER("Пользователь"),
    APPOINTMENT("Запись"),
    PET("Питомец"),
    SERVICE("Услуга");

    private final String description;

    LinkedEntityType(String description) {
        this.description = description;
    }
}
