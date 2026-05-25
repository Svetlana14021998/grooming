package org.example.enums.task;

import lombok.Getter;

@Getter
public enum TaskFor {

    ROLE("Группы пользователей"),
    USER("Одного пользователя")
    ;

    private final String description;

    TaskFor(String description) {
        this.description = description;
    }
}
