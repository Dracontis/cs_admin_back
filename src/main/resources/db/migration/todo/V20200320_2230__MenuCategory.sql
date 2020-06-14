create table if not exists CS.MENU_CATEGORY
(
    id bigserial primary key,
    name varchar(256) not null,

    created      timestamp default now(),
    lastmodified timestamp default now()
);



drop trigger if exists MENU_CATEGORY_LASTMOD on CS.MENU_CATEGORY;
create trigger MENU_CATEGORY_LASTMOD before update on CS.MENU_CATEGORY for each row execute procedure cs.sync_lastmod();