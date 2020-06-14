create table if not exists CS.MENU_ITEM
(
    id bigserial primary key,
    category_id bigint references CS.MENU_CATEGORY(id) not null,
    name varchar(128) not null,
    price numeric not null,
    carbohydrates numeric,
    proteins numeric,
    fats numeric,
    description text,
    display_date_from timestamp,

    created      timestamp default now(),
    lastmodified timestamp default now()
);

drop trigger if exists MENU_ITEM_LASTMOD on CS.MENU_ITEM;
create trigger MENU_ITEM_LASTMOD before update on CS.MENU_ITEM for each row execute procedure cs.sync_lastmod();