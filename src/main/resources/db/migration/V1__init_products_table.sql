drop table if exists products;
create table products(
    id SERIAL PRIMARY KEY,
    description varchar(100) not null,
    sold bit
);

create table product_groups(
    id SERIAL PRIMARY KEY,
    description varchar(100) not null
);

alter table products add column product_group_id int null;
alter table products
add foreign key (product_group_id) references product_groups (id);
alter table product_groups add column created_on timestamp null;
alter table product_groups add column updated_on timestamp null;