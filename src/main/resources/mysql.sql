create database myblog;
use myblog;

create table kind(
  id          VARCHAR(36) NOT NULL,
  name        VARCHAR(50) NOT NULL,
  create_time DATETIME,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `article` (
  `id`          varchar(36) NOT NULL,
  `title`       varchar(50) NOT NULL,
  `content`     longtext,
  `create_time` datetime DEFAULT NULL,
  `status`      TINYINT  DEFAULT 1,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

create table tag(
  id          VARCHAR(36) NOT NULL,
  name        VARCHAR(50) NOT NULL,
  create_time DATETIME,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table article_with_kind(
  id         VARCHAR(36) NOT NULL,
  article_id VARCHAR(36) NOT NULL,
  kind_id    VARCHAR(36) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table article_with_tag(
  id         VARCHAR(36) NOT NULL,
  article_id VARCHAR(36) NOT NULL,
  tag_id     VARCHAR(36) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user (
  id            VARCHAR(36) NOT NULL,
  user_name     VARCHAR(36) NOT NULL,
  user_password VARCHAR(36) NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE login_list (
  id            VARCHAR(36) NOT NULL,
  ip            VARCHAR(36) NOT NULL,
  success_count INT         NOT NULL DEFAULT 0,
  fail_count    INT         NOT NULL DEFAULT 0,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE black_list (
  id     VARCHAR(36)  NOT NULL,
  ip     VARCHAR(36)  NOT NULL,
  remark VARCHAR(500) NULL DEFAULT '该ip列为黑名单',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;