create database myblog;


use myblog;


create table kind(
	id varchar(36) not null,
	name varchar(50) not null,
	primary key(id)
)

create table article(
	id varchar(36) not null,
	name varchar(50) not null,
	create_time datetime,
	primary key(id)
)

create table tag(
	id varchar(36) not null,
	name varchar(50) not null,
	create_time datetime,
	primary key(id)
)
