package org.example.enums.task;

import lombok.Getter;

@Getter
public enum Priority {

    HIGH("Высокий"),
    MIDDLE("Средний"),
    LOW("Низкий");

    private final String description;

    Priority(String description) {
        this.description = description;
    }
}
