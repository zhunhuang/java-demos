use `baby_record`;

drop table  if exists baby_info;
CREATE TABLE `baby_info`
(
  `id`         bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `baby_id`    varchar(32)         NOT NULL DEFAULT '' COMMENT '宝宝唯一id',
  `baby_name`  varchar(64)         NOT NULL DEFAULT '' COMMENT '宝宝nick名字',
  `icon_url`   varchar(1024)        NOT NULL DEFAULT '' COMMENT '宝宝icon',
  `background_url`   varchar(1024)        NOT NULL DEFAULT '' COMMENT '宝宝头图url',
  `gmt_create` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  `gmt_update` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_baby_id` (`baby_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='宝宝信息表';

drop table  if exists file_info;
CREATE TABLE `file_info`
(
  `id`                 bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `resource_id`        varchar(1024)       NOT NULL DEFAULT '' COMMENT '资源来源',
  `file_original_name` varchar(1024)       NOT NULL DEFAULT '' COMMENT '文件原始名字',
  `file_type`          varchar(64)         NOT NULL DEFAULT '0' COMMENT '文件类型',
  `file_size`          bigint(20)          NOT NULL DEFAULT 0 COMMENT '文件大小，单位字节',
  `file_md5`           varchar(130)        NOT NULL DEFAULT '' COMMENT '文件md5校验',
  `file_name`          varchar(128)        NOT NULL DEFAULT '' COMMENT '文件类型',
  `file_path`          varchar(512)        NOT NULL DEFAULT '' COMMENT '文件路径',
  `file_url`          varchar(512)        NOT NULL DEFAULT '' COMMENT '文件链接',
  `valid`              bool                NOT NULL DEFAULT false COMMENT '是否有效',
  `gmt_create`         timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  `gmt_update`         timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_file_name` (`file_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='文件信息表';

drop table if exists `record_info`;
CREATE TABLE `record_info`
(
  `id`         bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `baby_id`    varchar(32)         NOT NULL DEFAULT '' COMMENT '宝宝唯一id',
  `user_id`    varchar(32)         NOT NULL DEFAULT '' COMMENT '发表人用户id',
  `img_urls`   varchar(2048)         NOT NULL DEFAULT '' COMMENT '图片链接列表',
  `content`    varchar(64)         NOT NULL DEFAULT '' COMMENT '朋友圈内容',
  `gmt_create` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  `gmt_update` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_baby_id` (`baby_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='朋友圈信息表';

CREATE TABLE `comment_info`
(
  `id`             bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `record_id`      bigint(20) unsigned NOT NULL COMMENT '关联的主键',
  `commenter_id`   varchar(32)         NOT NULL DEFAULT '' COMMENT '评论人id',
  `commenter_name` varchar(32)         NOT NULL DEFAULT '' COMMENT '评论人名字',
  `content` varchar(2048)         NOT NULL DEFAULT '' COMMENT '评论',
  `replied_id`     varchar(32)         NOT NULL DEFAULT '' COMMENT '图片链接列表',
  `replied_name`   varchar(32)         NOT NULL DEFAULT '' COMMENT '图片链接列表',
  `gmt_create`     timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  `gmt_update`     timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_record_id` (`record_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='评论信息表';

create table `user_baby_relation`
(
  `id`              bigint(20) unsigned not null auto_increment comment '自增主键',
  `baby_id`         varchar(32)         NOT NULL DEFAULT '' COMMENT '宝宝唯一id',
  `user_id`         varchar(32)         NOT NULL DEFAULT '' COMMENT '用户唯一id',
  `privilege_level` smallint            not null default -1 comment '权限等级，越小越高',
  `gmt_create`      timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  `gmt_update`      timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_baby_id` (`baby_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户宝宝关系表';
