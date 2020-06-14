create table if not exists CS.CAFE
(
    id bigserial primary key,
    name varchar(256) not null,
    address text not null,
    image_url varchar(2048) not null,

    created      timestamp default now(),
    lastmodified timestamp default now()
);

drop trigger if exists CAFE_LASTMOD on CS.CAFE;
create trigger CAFE_LASTMOD before update on CS.CAFE for each row execute procedure cs.sync_lastmod();