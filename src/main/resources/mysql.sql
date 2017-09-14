create database myblog;
use myblog;

create table kind(
	id varchar(36) not null,
	name varchar(50) not null,
	create_time datetime,
	primary key;(id)
);

CREATE TABLE `article` (
   `id` varchar(36) NOT NULL,
   `title` varchar(50) NOT NULL,
   `content` longtext,
   `create_time` datetime DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8

create table tag(
	id varchar(36) not null,
	name varchar(50) not null,
	create_time datetime,
	primary key(id)
);


create table article_with_kind(
	id varchar(36) not null,
	article_id  varchar(36) not null,
	kind_id  varchar(36) not null,
	primary key(id)
);

create table article_with_tag(
	id varchar(36) not null,
	article_id  varchar(36) not null,
	tag_id  varchar(36) not null,
	primary key(id)
);