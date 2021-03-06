DROP DATABASE IF EXISTS myblog;
CREATE DATABASE myblog;
USE myblog;

DROP TABLE IF EXISTS kind;
CREATE TABLE kind (
  id          VARCHAR(36) NOT NULL,
  name        VARCHAR(50) NOT NULL,
  create_time DATETIME,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS article;
CREATE TABLE `article` (
  `id`          VARCHAR(36) NOT NULL,
  `title`       VARCHAR(50) NOT NULL,
  `content`     LONGTEXT,
  `create_time` DATETIME DEFAULT NULL,
  `status`      TINYINT  DEFAULT 1,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS tag;
CREATE TABLE tag (
  id          VARCHAR(36) NOT NULL,
  name        VARCHAR(50) NOT NULL,
  create_time DATETIME,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS article_with_kind;
CREATE TABLE article_with_kind (
  id         VARCHAR(36) NOT NULL,
  article_id VARCHAR(36) NOT NULL,
  kind_id    VARCHAR(36) NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS article_with_tag;
CREATE TABLE article_with_tag (
  id         VARCHAR(36) NOT NULL,
  article_id VARCHAR(36) NOT NULL,
  tag_id     VARCHAR(36) NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id            VARCHAR(36) NOT NULL,
  user_name     VARCHAR(36) NOT NULL,
  user_password VARCHAR(36) NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS login_list;
CREATE TABLE login_list (
  id            VARCHAR(36) NOT NULL,
  ip            VARCHAR(36) NOT NULL,
  success_count INT         NOT NULL DEFAULT 0,
  fail_count    INT         NOT NULL DEFAULT 0,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS black_list;
CREATE TABLE black_list (
  id     VARCHAR(36)  NOT NULL,
  ip     VARCHAR(36)  NOT NULL,
  remark VARCHAR(500) NULL DEFAULT '该ip列为黑名单',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;