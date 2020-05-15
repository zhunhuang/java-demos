CREATE TABLE user
(
    `id`       bigint unsigned auto_increment comment '自增id',
    `name`     VARCHAR(32) NOT NULL DEFAULT ''
        COMMENT '用户名',
    `password` VARCHAR(32) NOT NULL DEFAULT ''
        COMMENT '密码'
);

CREATE TABLE student
(
    `id`   bigint unsigned auto_increment comment '自增id',
    `name` VARCHAR(32) NOT NULL DEFAULT ''
        COMMENT '用户名',
    `age`  int         NOT NULL DEFAULT ''
        COMMENT '年龄'
);