create table products
(
    id          SERIAL PRIMARY KEY,
    name        varchar(100) not null,
    description varchar(100) not null,
    price       integer,
    sold        boolean,
    created_on  timestamp,
    updated_on  timestamp
);

create table product_groups
(
    id          SERIAL PRIMARY KEY,
    name        varchar(100) not null,
    description varchar(100) not null,
    created_on  timestamp,
    updated_on  timestamp
);

alter table products
    add column product_group_id int null;
alter table products
    add foreign key (product_group_id) references product_groups (id);
