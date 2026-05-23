package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.model.task.Task;
import org.example.repository.TaskRepository;
import org.example.service.TaskService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public <T extends Task> Task save(T task) {
        return taskRepository.save(task);
    }
}
