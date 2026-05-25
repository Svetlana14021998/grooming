create table animal_types
(
    id      bigserial,
    icon    varchar(20),
    name_en varchar(20) not null,
    name_ru varchar(20) not null,
    primary key (id)
);
create table grooming_services
(
    id             bigserial,
    category       varchar(40),
    description_en varchar(255)   not null,
    description_ru varchar(255)   not null,
    duration       integer        not null,
    is_popular     boolean        not null,
    name_en        varchar(70)   not null,
    name_ru        varchar(70)   not null,
    photo_name     varchar(20),
    price          DECIMAL(8, 2) not null,
    primary key (id)
);
create table service_animal_types(
                                     service_id bigint references grooming_services(id) on delete cascade,
                                     animal_type_id bigint references animal_types(id) on delete cascade,
                                     primary key (service_id, animal_type_id)

);