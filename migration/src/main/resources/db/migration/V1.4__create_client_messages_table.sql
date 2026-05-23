create table client_messages
(
    id bigserial,
    name varchar(255),
    email varchar(255),
    phone varchar(255),
    message TEXT,
    email_sent boolean
);