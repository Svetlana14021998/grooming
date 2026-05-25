create table tasks
(
    id               BIGSERIAL PRIMARY KEY,

    task_type        VARCHAR(31) NOT NULL,
    text             VARCHAR(255),
    comment          TEXT,
    priority         VARCHAR(20),
    type             VARCHAR(255),
    status           VARCHAR(20)  DEFAULT 'NEW',
    task_for         VARCHAR(20),

    link_entity_id   BIGINT,
    link_entity_type VARCHAR(50),

    role             VARCHAR(255) DEFAULT 'ROLE_MANAGER'
);

CREATE INDEX idx_task_type ON tasks (task_type);
CREATE INDEX idx_status ON tasks (status);
CREATE INDEX idx_task_for ON tasks (task_for);
CREATE INDEX idx_link_entity ON tasks (link_entity_type, link_entity_id);
CREATE INDEX idx_role ON tasks (role);
);