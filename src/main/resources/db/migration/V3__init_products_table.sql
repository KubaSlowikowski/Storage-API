create table products
(
    id               bigserial PRIMARY KEY,
    name             varchar(100) not null,
    description      varchar(100) not null,
    price            integer,
    sold             boolean,
    product_group_id bigserial references product_groups (id),
    created_on       timestamp,
    updated_on       timestamp,
    created_by       bigint null,
    modified_by      bigint null
);
