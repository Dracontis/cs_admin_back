create table if not exists CS.USER
(
    id           bigserial primary key,
    username     varchar(32) not null,
    password     varchar(512) not null,
    role         varchar(32) not null,
    created      timestamp default now(),
    lastmodified timestamp default now()
);

insert into CS.USER (username, password, role) values ('admin', '$2a$10$SJKXDzeu3nkjIIEri2ZfZ.JU8W/ceW79sMJS57xeI68DW42giol.u', 'ADMINISTRATOR');