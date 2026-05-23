package org.example.service;

import org.example.model.task.Task;

public interface TaskService {
    <T extends Task> Task save(T task);
}
