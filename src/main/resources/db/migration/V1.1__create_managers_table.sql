create table masters
(
    id            bigserial,
    en_experience varchar(255) not null,
    en_full_name  varchar(70) not null,
    en_specialisation  varchar(100) not null,
    ru_experience varchar(255) not null,
    ru_full_name  varchar(70) not null,
    ru_specialisation  varchar(100) not null,
    photo_name  varchar(20) not null,
    primary key (id)
);