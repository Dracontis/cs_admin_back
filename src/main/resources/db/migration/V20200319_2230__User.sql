do
$$
    begin
        if not exists(select 1 from pg_type where typname = 'USER_ROLE') then
            create type CS.USER_ROLE as enum (
                'ADMINISTRATOR', 'MANAGER', 'STAFF'
                );
            create cast ( character varying as cs.user_role ) with inout as assignment;
        end if;
    end
$$;

create table if not exists CS.USER
(
    id           bigserial primary key,
    username     varchar(32) not null,
    password     varchar(512) not null,
    role         CS.USER_ROLE not null,
    created      timestamp default now(),
    lastmodified timestamp default now()
);

drop trigger if exists USER_LASTMOD on CS.USER;
create trigger USER_LASTMOD before update on CS.USER for each row execute procedure cs.sync_lastmod();

insert into CS.USER (username, password, role) values ('admin', '$2a$10$SJKXDzeu3nkjIIEri2ZfZ.JU8W/ceW79sMJS57xeI68DW42giol.u', 'ADMINISTRATOR');