create table product_groups
(
    id          bigserial PRIMARY KEY,
    name        varchar(100) not null,
    description varchar(100) not null,
    created_on  timestamp,
    updated_on  timestamp,
    created_by  bigint null,
    modified_by bigint null
);
