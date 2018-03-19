--liquibase formatted sql

--changeset kafkademo:1

create table organization (
    id serial not null,
    organization_name varchar(255) not null,
    ceo varchar(255) not null,
    created_date timestamp default current_timestamp not null,
    last_modified_date timestamp default current_timestamp not null,
    primary key (id)
);

