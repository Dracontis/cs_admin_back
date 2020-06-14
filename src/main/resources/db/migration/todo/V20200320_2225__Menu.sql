create table if not exists CS.MENU
(
    id bigserial primary key,

    created      timestamp default now(),
    lastmodified timestamp default now()
);

drop trigger if exists MENU_LASTMOD on CS.MENU;
create trigger MENU_LASTMOD before update on CS.MENU for each row execute procedure cs.sync_lastmod();

create table if not exists CS.MENU_TO_CAFE
(
    cafe_id bigint references CS.CAFE(id) not null,
    menu_id bigint references CS.MENU(id) not null
)