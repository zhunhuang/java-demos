CREATE TABLE user (
  `name`     VARCHAR(32) NOT NULL DEFAULT ''
  COMMENT '用户名',
  `password` VARCHAR(32) NOT NULL DEFAULT ''
  COMMENT '密码'
);