package org.example.enums.task;

import lombok.Getter;

@Getter
public enum TaskStatus {

    NEW("Новая"),
    IN_PROGRESS("В работе"),
    CLOSE("Закрыта");

    private final String description;

    TaskStatus(String description) {
        this.description = description;
    }
}
