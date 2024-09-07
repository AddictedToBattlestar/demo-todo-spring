-- liquibase formatted sql
-- changeset addictedtobattlestar:setup-envers
-- comment Creation of tables to support envers
create table revinfo
(
    rev      int auto_increment primary key,
    revtstmp bigint null
);

create table todo_aud
(
    id          bigint       not null,
    rev         int          not null,
    revtype     tinyint      null,
    complete    bit          null,
    title       varchar(50)  null,
    description varchar(255) null,
    due_date    datetime     null,
    primary key (rev, id),
    constraint FK_todo_aud_revinfo
        foreign key (rev) references revinfo (rev)
);
