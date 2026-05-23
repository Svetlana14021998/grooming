package org.example.listener;

import jakarta.persistence.PostPersist;
import lombok.extern.slf4j.Slf4j;
import org.example.model.ClientMessage;
import org.example.model.Feedback;
import org.example.model.task.ProcessClientMessageTask;
import org.example.model.task.ProcessFeedbackTask;
import org.example.model.task.Task;
import org.example.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class FeedbackListener {

    private TaskService taskService;

    @Autowired
    public FeedbackListener(@Lazy TaskService taskService) {
        this.taskService = taskService;
    }

    @PostPersist
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void onPostPersist(Feedback feedback) {
        ProcessFeedbackTask task = new ProcessFeedbackTask(feedback.getId());
        Task savedTask = taskService.save(task);
        log.info("Create task with id {}", savedTask.getId());
    }
}
