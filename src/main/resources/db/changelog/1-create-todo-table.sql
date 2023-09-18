-- liquibase formatted sql
-- changeset addictedtobattlestar:todo-table-creation
-- comment Creation of the todo table
create table todo
(
    id          bigint auto_increment primary key,
    complete    bit          not null,
    title varchar(50) null,
    description varchar(255) null,
    due_date    datetime null
);
