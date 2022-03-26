drop table if exists users;
drop table if exists authorities;
drop table if exists user_statistics;

create table users(
	username varchar(50) not null primary key,
	password varchar(60) not null,
	enabled boolean not null
);

create table authorities (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);


create table user_statistics(
	username varchar(50) not null primary key,
    correct_guesses int(11) default 0,
    all_guesses int(11) default 0
);


insert into users(username, password, enabled) values ('user', '$2a$10$LHQEk9LydC.Z9uoeUj7N7O0Z0neWaf4cAqU6UskJRtuRJaSybomPu', 1);
insert into authorities(username, authority) values ('user', 'ROLE_USER');
insert into user_statistics(username, correct_guesses, all_guesses) values ('user', 0, 0);