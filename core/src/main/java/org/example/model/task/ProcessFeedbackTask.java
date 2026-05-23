package org.example.model.task;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.example.enums.task.LinkedEntityType;
import org.example.enums.task.Priority;
import org.example.enums.task.TaskFor;

@Entity
@DiscriminatorValue("PROCESS_FEEDBACK_TASK")
@Getter
public class ProcessFeedbackTask extends Task {

    @Column(name = "role")
    private String roleName = "ROLE_MANAGER";

    public ProcessFeedbackTask(Long linkEntityId) {
        super.setPriority(Priority.MIDDLE);
        super.setTaskFor(TaskFor.ROLE);
        super.setType("Обработка обратной связи от клиента");
        super.setText("Был получен отзыв от клиента. Необходимо ознакомиться с ним и, при необходимости, связаться с клиентом");
        super.setLinkEntityId(linkEntityId);
        super.setLinkedEntityType(LinkedEntityType.FEEDBACK);
    }
}
