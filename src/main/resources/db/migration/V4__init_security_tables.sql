create table roles
(
    id   integer PRIMARY KEY,
    name varchar(20) not null
);

create table users
(
    id       integer PRIMARY KEY,
    username varchar(20)  not null,
    email    varchar(50)  not null,
    password varchar(120) not null
);

create table user_roles
(
    user_id integer,
    role_id integer
);

alter table user_roles
    add foreign key (user_id) references users (id);
alter table user_roles
    add foreign key (role_id) references roles (id);
