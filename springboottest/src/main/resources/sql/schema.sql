CREATE TABLE user
(
    `id`   bigint unsigned auto_increment comment '自增id',
    `name` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '用户名',
    `age`  int         NOT NULL DEFAULT 0 COMMENT '年龄',
    primary key (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';