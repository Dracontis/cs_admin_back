do
$$
    begin
        if not exists(select 1 from pg_type where typname = 'ORDER_STATUS') then
            create type CS.ORDER_STATUS as enum (
                'CREATED', 'PREPARED', 'SERVED'
                );
        end if;
    end
$$;

create table if not exists CS.ORDER
(
    id bigserial primary key,
    cafe_id bigint references CS.CAFE(id) not null,
    user_id varchar(36) not null,
    status CS.ORDER_STATUS not null,

    created      timestamp default now(),
    lastmodified timestamp default now()
);

drop trigger if exists ORDER_LASTMOD on CS.ORDER;
create trigger ORDER_LASTMOD before update on CS.ORDER for each row execute procedure cs.sync_lastmod();

create table if not exists CS.MENU_ITEM_TO_ORDER
(
    order_id bigint references CS.ORDER(id) not null,
    menu_item_id bigint references CS.MENU_ITEM(id) not null
)