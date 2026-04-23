create table feedback
(
    id            bigserial,
    client_name   varchar(255),
    creation_time timestamp(6),
    email         varchar(255),
    pet_name      varchar(255),
    phone         varchar(255),
    review        TEXT,
    score         integer,
    visit_date    date,
    master_id     bigint references masters (id) on delete cascade,
    primary key (id)
);
