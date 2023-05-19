create table person
(
    id         varchar(255) primary key,
    name       varchar(255),
    surname    varchar(255),
    email      varchar(255) unique,
    created_at timestamp
);