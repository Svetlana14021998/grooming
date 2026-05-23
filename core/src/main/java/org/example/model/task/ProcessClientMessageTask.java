package org.example.model.task;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.example.enums.task.LinkedEntityType;
import org.example.enums.task.Priority;
import org.example.enums.task.TaskFor;

@Entity
@DiscriminatorValue("PROCESS_CLIENT_MESSAGE_TASK")
@Getter
public class ProcessClientMessageTask extends Task {

    @Column(name = "role")
    private String roleName = "ROLE_MANAGER";

    public ProcessClientMessageTask(Long linkEntityId) {
        super.setPriority(Priority.MIDDLE);
        super.setTaskFor(TaskFor.ROLE);
        super.setType("Обработка обращения от клиента с сайта");
        super.setText("Был задан вопрос через форму обратной связи. Необходимо ознакомиться с ним и, при необходимости, связаться с клиентом");
        super.setLinkEntityId(linkEntityId);
        super.setLinkedEntityType(LinkedEntityType.FEEDBACK);
    }
}
