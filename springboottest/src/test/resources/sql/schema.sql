--  auto generated by TransformMysqlToH2 
SET MODE MYSQL;

CREATE TABLE user
(
    id   bigint unsigned auto_increment comment '自增id',
    name VARCHAR(32) NOT NULL DEFAULT '' ,
    age  int         NOT NULL DEFAULT 0 ,
    primary key (id)
);