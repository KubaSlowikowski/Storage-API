alter table product_groups
    add column created_by varchar(100) null;
alter table product_groups
    add column modified_by varchar(100) null;
alter table products
    add column created_by varchar(100) null;
alter table products
    add column modified_by varchar(100) null;
