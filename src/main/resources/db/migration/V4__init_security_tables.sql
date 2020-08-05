create table roles
(
    id   serial PRIMARY KEY,
    name varchar(20) not null
);

create table users
(
    id             bigserial PRIMARY KEY,
    username       varchar(20)  not null,
    email          varchar(50)  not null,
    password       varchar(120) not null,
    created_on     timestamp
);

create table user_roles
(
    user_id bigserial references users (id),
    role_id serial references roles (id)
);
