DROP SCHEMA IF EXISTS encounter_service CASCADE;

CREATE SCHEMA IF NOT EXISTS encounter_service;

SET SCHEMA 'encounter_service';

CREATE TABLE IF NOT EXISTS monster_view
(
    id   BIGINT primary key,
    name VARCHAR(255)  not null,
    cr   DECIMAL(3, 1) not null
);

CREATE TABLE IF NOT EXISTS monster_monster_group
(
    monster_id       BIGINT not null,
    monster_group_id BIGINT not null,
    foreign key (monster_id) references monster_view (id),
    primary key (monster_id, monster_group_id)
);