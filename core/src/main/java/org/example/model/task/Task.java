package org.example.model.task;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.enums.task.LinkedEntityType;
import org.example.enums.task.Priority;
import org.example.enums.task.TaskFor;
import org.example.enums.task.TaskStatus;

@Entity
@Table(name = "tasks")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@DiscriminatorColumn(name = "task_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "comment")
    private String comment;

    @Column(name = "priority")
    @Enumerated(value = EnumType.STRING)
    private Priority priority;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private TaskStatus status = TaskStatus.NEW;

    @Column(name = "task_for")
    @Enumerated(value = EnumType.STRING)
    private TaskFor taskFor;

    @Column(name = "link_entity_id")
    private Long linkEntityId;

    @Column(name = "link_entity_type")
    @Enumerated(value = EnumType.STRING)
    private LinkedEntityType linkedEntityType;
}
