create table masters
(
    id            bigserial,
    en_experience varchar(255) not null,
    en_full_name  varchar(70) not null,
    ru_experience varchar(255) not null,
    ru_full_name  varchar(70) not null,
    photo_name  varchar(20) not null,
    primary key (id)
);

create table masters_services(
    master_id bigint,
    service_id bigint,
    primary key (master_id,service_id)
);