create table client_messages
(
    id bigserial,
    name varchar(255),
    email varchar(255),
    phone varchar(255),
    message TEXT,
    email_sent boolean,
    email_sent_at timestamp(6),
    email_error varchar(255)
);