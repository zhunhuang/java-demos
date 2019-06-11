CREATE TABLE `membership_card_type`(
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `type_name` varchar(64) NOT NULL DEFAULT '' COMMENT '类型名称，vip会员卡',
  `select_type` INT(2) NOT NULL DEFAULT '0' COMMENT '套餐选择类型，(0:单选， 1:多选组合)',
  `card_type_enum` INT(2) NOT NULL DEFAULT '0' comment '卡类型（0：试用卡，1：付费卡，2：赠送卡），枚举',
  `type_status` INT(2) NOT NULL DEFAULT '0' comment '卡上下线状(0:上线，1：下线)',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' comment '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_name` (`type_name`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员卡类型表';

CREATE TABLE `membership_card`(
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `card_code` varchar(32) NOT NULL DEFAULT '' COMMENT '卡编码',
  `card_name` varchar(64) NOT NULL DEFAULT '' COMMENT '卡名字',
  `card_type` INT(2) NOT NULL DEFAULT '0' comment '卡类型（0：试用卡，1：付费卡，2：赠送卡）',
  `sale_price` decimal(10,2) NOT NULL DEFAULT '0.00' comment '卡售价',
  `reference_price` decimal(10,2) NOT NULL DEFAULT '0.00' comment '卡参考价格（权益总价格）',
  `total_stock` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '卡库存',
  `use_stock` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `card_content` varchar(1024) NOT NULL DEFAULT '' COMMENT '卡套餐内容（GBT09097-1-3-Y,ZSKF8873-0-0-N)',
  `validity_time` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '卡有效期(单位：日)',
  `begin_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '活动开始时间',
  `end_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '活动截止时间',
  `card_status` INT(2) NOT NULL DEFAULT '0' comment '卡上下线状(0:上线，1：下线)',
  `card_describe` varchar(1024) NOT NULL DEFAULT '' COMMENT '卡描述',
  `use_limit` TEXT COMMENT '卡的使用限制说明',
  `disclaimer` TEXT COMMENT '免责声明',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' comment '创建时间',
  `last_update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `bind_num` INT(11) NOT NULL DEFAULT '1' COMMENT '卡可绑定受益人数',
  `bind_type` TINYINT(2) NOT NULL DEFAULT '0' COMMENT '绑定类型',
  `icon_url` VARCHAR (255) NOT NULL DEFAULT '' COMMENT '会员卡的icon_url',
  `banner_url` VARCHAR (255) NOT NULL DEFAULT '' COMMENT '会员卡的背景url',
  `rgb_code` VARCHAR (32) NOT NULL DEFAULT '' COMMENT '会员卡主题色的RGB值',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_card_code` (`card_code`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_last_update_time` (`last_update_time`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员卡信息表';

CREATE TABLE `membership_privilege`(
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `privilege_code` varchar(32) NOT NULL DEFAULT '' COMMENT '特权编码',
  `privilege_name` varchar(64) NOT NULL DEFAULT '' COMMENT '特权名称',
  `privilege_type` INT(2) NOT NULL DEFAULT '0' comment '特权类型（0:X,1:代金券,2:专属服务)',
  `grant_type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '权益发放规则(0:不分期发放,1:分期发放)',
  `association_code` varchar(32) NOT NULL DEFAULT '' comment '关联系统编码（X系统的大类）',
  `settle_price` decimal(10,2) NOT NULL DEFAULT '0.00' comment '权益结算价格',
  `privilege_brief` varchar(1024) NOT NULL DEFAULT '' COMMENT '权益简介（专车接送出行无忧）',
  `privilege_desc` Text COMMENT '权益的使用说明（html）',
  `icon_url` varchar(256) NOT NULL DEFAULT '' COMMENT 'iconUrl',
  `black_icon_url` varchar(256) NOT NULL DEFAULT '' COMMENT '置灰的iconUrl',
  `detail_pic_url` varchar(256) NOT NULL DEFAULT '' COMMENT '特权详情图片url',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' comment '创建时间',
  `last_update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_privilege_code` (`privilege_code`),
  KEY `idx_association_code` (`association_code`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_last_update_time` (`last_update_time`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='特权信息表';


CREATE TABLE `card_sale_order_1`(
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `order_no` varchar(32) NOT NULL DEFAULT '' COMMENT '购买订单号,卡单号',
  `card_no` varchar(32) NOT NULL DEFAULT '' COMMENT '卡号',
  `card_code` varchar(32) NOT NULL DEFAULT '' COMMENT '卡编码',
  `user_name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' comment '售卖金额',
  `order_status` INT(2) NOT NULL DEFAULT '0' comment '订单状态（0生单，1取消，2支付，3绑定）',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' comment '创建时间',
  `last_update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `card_num` INT NOT NULL DEFAULT '0' comment '购买卡数量',
  `card_unit_price` decimal(10,2) NOT NULL DEFAULT '0.00' comment '卡的售卖单价',
  `channel` INT(2) NOT NULL DEFAULT '1' comment '订单渠道',
  `parent_order_no` varchar(255) NOT NULL DEFAULT '' COMMENT '父订单号',
  `real_price` decimal(10,2) NOT NULL DEFAULT '0.00' comment '用户支付金额',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_order_no` (`order_no`),
  KEY `idx_user_name_card_code` (`user_name`,`card_code`),
  KEY `idx_user_name_order_status` (`user_name`,`order_status`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_last_update_time` (`last_update_time`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='卡售卖订单表';

CREATE TABLE `card_sale_order_2` like `card_sale_order_1`;
CREATE TABLE `card_sale_order_3` like `card_sale_order_1`;
CREATE TABLE `card_sale_order_4` like `card_sale_order_1`;



CREATE TABLE `card_sale_ext_1`(
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `order_no` varchar(32) NOT NULL DEFAULT '' COMMENT '购买订单号,卡单号',
  `user_name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `ext_key` varchar(32) NOT NULL DEFAULT '' COMMENT '扩展字段key',
  `ext_value` varchar(1024) NOT NULL DEFAULT '' COMMENT '扩展字段value',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' comment '创建时间',
  `last_update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_order_no_key` (`order_no`, `ext_key`),
  KEY `idx_user_name_order_no` (`user_name`, `order_no`),
  KEY `idx_ext_key` (`ext_key`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_last_update_time` (`last_update_time`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='卡售卖订单扩展表';

CREATE TABLE `card_sale_ext_2` like `card_sale_ext_1`;
CREATE TABLE `card_sale_ext_3` like `card_sale_ext_1`;
CREATE TABLE `card_sale_ext_4` like `card_sale_ext_1`;


CREATE TABLE `card_sale_pay_record_1`(
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `order_no` varchar(32) NOT NULL DEFAULT '' COMMENT '购买订单号',
  `user_name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `sale_price` decimal(10,2) NOT NULL DEFAULT '0.00' comment '售卖金额',
  `merchant_code`varchar(20)NOT NULL DEFAULT''COMMENT'商户号',
  `busi_type`varchar(20) NOT NULL DEFAULT''COMMENT'业务系统编号',
  `trans_type`varchar(20) NOT NULL DEFAULT''COMMENT'交易类型',
  `share_data`varchar(200) NOT NULL DEFAULT''COMMENT'分账公式',
  `inner_trans_id`varchar(50) NOT NULL DEFAULT''COMMENT'子交易流水号',
  `pm_code`varchar(25) NOT NULL DEFAULT''COMMENT'支付方式',
  `tpp_code`varchar(20) NOT NULL DEFAULT''COMMENT'网关编号',
  `bank_code`varchar(20) NOT NULL DEFAULT''COMMENT'银行编号',
  `transaction_id`varchar(100)NOT NULL DEFAULT''COMMENT'支付中心,外部交易流水号',
  `status` tinyint(4) NOT NULL DEFAULT'0'COMMENT'订单状态（0去支付，1支付成功）',
  `paid_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' comment '用户支付时间',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' comment '创建时间',
  `last_update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_inner_trans_id` (`inner_trans_id`),
  KEY `idx_user_name_order_no` (`user_name`,`order_no`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_last_update_time` (`last_update_time`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='卡售卖支付记录表';

CREATE TABLE `card_sale_pay_record_2` like `card_sale_pay_record_1`;
CREATE TABLE `card_sale_pay_record_3` like `card_sale_pay_record_1`;
CREATE TABLE `card_sale_pay_record_4` like `card_sale_pay_record_1`;

CREATE TABLE `membership_account_1`(
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `card_no` varchar(32) NOT NULL DEFAULT '' COMMENT '卡号',
  `card_code` varchar(32) NOT NULL DEFAULT '' COMMENT '卡编码',
  `user_name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `beneficiary_name` varchar(32) NOT NULL DEFAULT '' COMMENT ' 绑定受益人名称',
  `beneficiary_card_type` INT(2) NOT NULL DEFAULT '0' comment '绑定受益人证件类型',
  `beneficiary_card_no` varchar(128) NOT NULL DEFAULT '' COMMENT ' 绑定受益人证件号',
  `account_status` INT(2) NOT NULL DEFAULT '0' comment '账户状态(0使用，1作废，2过期)',
  `begin_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '账户有效开始时间',
  `end_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '账户有效截止时间',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' comment '创建时间',
  `last_update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `order_id` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '购买订单号,卡id',
  `order_no` varchar(32) NOT NULL DEFAULT '' COMMENT '购买订单号,卡单号',
  `bind_num` INT(11) NOT NULL DEFAULT '1' COMMENT '卡账户可绑定受益总人数',
  `card_alias` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '卡账户别名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_card_no` (`card_no`),
  KEY `idx_user_name_card_no` (`user_name`,`card_no`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_last_update_time` (`last_update_time`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='特权用户账户信息表';

CREATE TABLE `membership_account_2` like `membership_account_1`;
CREATE TABLE `membership_account_3` like `membership_account_1`;
CREATE TABLE `membership_account_4` like `membership_account_1`;


CREATE TABLE `membership_info_1`(
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_name` varchar(32) NOT NULL DEFAULT '' COMMENT '登录用户名',
  `card_no` varchar(32) NOT NULL DEFAULT '' COMMENT '特权卡号',
  `privilege_code` varchar(32) NOT NULL DEFAULT '' comment '特权编码',
  `limit_num` INT NOT NULL DEFAULT '0' comment '剩余享用次数',
  `total_num` INT NOT NULL DEFAULT '0' comment '权益总数',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' comment '创建时间',
  `last_update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `use_num` INT NOT NULL DEFAULT '0' comment '使用次数',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' comment '权益单价（返现权益即表示每次返现金额）',
  `expire_num` INT NOT NULL DEFAULT '0' comment '过期次数',
  `privilege_type` INT(2) NOT NULL DEFAULT '-1' comment '特权类型（0:X,1:代金券,2:专属服务,3:返现)',
  PRIMARY KEY (`id`),
  KEY `idx_user_name_card_no` (`user_name`,`card_no`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_last_update_time` (`last_update_time`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户特权信息表';


CREATE TABLE `membership_info_2` like `membership_info_1`;
CREATE TABLE `membership_info_3` like `membership_info_1`;
CREATE TABLE `membership_info_4` like `membership_info_1`;
CREATE TABLE `membership_info_5` like `membership_info_1`;
CREATE TABLE `membership_info_6` like `membership_info_1`;
CREATE TABLE `membership_info_7` like `membership_info_1`;
CREATE TABLE `membership_info_8` like `membership_info_1`;
CREATE TABLE `membership_info_9` like `membership_info_1`;
CREATE TABLE `membership_info_10` like `membership_info_1`;
CREATE TABLE `membership_info_11` like `membership_info_1`;
CREATE TABLE `membership_info_12` like `membership_info_1`;
CREATE TABLE `membership_info_13` like `membership_info_1`;
CREATE TABLE `membership_info_14` like `membership_info_1`;
CREATE TABLE `membership_info_15` like `membership_info_1`;
CREATE TABLE `membership_info_16` like `membership_info_1`;

CREATE TABLE `membership_detail_1`(
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `flight_order_no` varchar(64) NOT NULL DEFAULT '' COMMENT '机票订单号',
  `user_name` varchar(32) NOT NULL DEFAULT '' COMMENT '登录用户名',
  `card_no` varchar(32) NOT NULL DEFAULT '' COMMENT '付费卡号',
  `privilege_code` varchar(32) NOT NULL DEFAULT '' COMMENT '特权编码',
  `use_status` INT(2) NOT NULL DEFAULT '0' comment '权益享用状态(1收回，2使用，0不变)',
  `count` INT NOT NULL DEFAULT '0' comment '特权使用次数',
  `passenger_key` INT(2) NOT NULL DEFAULT '0' comment '乘机人key',
  `price` varchar(32) NOT NULL DEFAULT '' COMMENT '生单抵扣金额',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' comment '创建时间',
  `last_update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `card_code`  varchar(32) NOT NULL DEFAULT '' COMMENT '卡编码',
  `privilege_type` INT(2) NOT NULL DEFAULT '0' comment '特权类型（0:X,1:代金券,2:专属服务,3:返现)',
  `passenger_name` varchar(255) NOT NULL DEFAULT '' COMMENT '乘机人姓名列表',
  `privilege_grant_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '权益发放的id',
  `issue_no` TINYINT(4) NOT NULL DEFAULT 0 COMMENT '权益发放期数',
  PRIMARY KEY (`id`),
  KEY `idx_user_name` (`user_name`),
  KEY `idx_flight_order_no` (`flight_order_no`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_last_update_time` (`last_update_time`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='特权卡享用明细';

CREATE TABLE `membership_detail_2` like `membership_detail_1`;
CREATE TABLE `membership_detail_3` like `membership_detail_1`;
CREATE TABLE `membership_detail_4` like `membership_detail_1`;
CREATE TABLE `membership_detail_5` like `membership_detail_1`;
CREATE TABLE `membership_detail_6` like `membership_detail_1`;
CREATE TABLE `membership_detail_7` like `membership_detail_1`;
CREATE TABLE `membership_detail_8` like `membership_detail_1`;
CREATE TABLE `membership_detail_9` like `membership_detail_1`;

CREATE TABLE `membership_detail_10` like `membership_detail_1`;
CREATE TABLE `membership_detail_11` like `membership_detail_1`;
CREATE TABLE `membership_detail_12` like `membership_detail_1`;
CREATE TABLE `membership_detail_13` like `membership_detail_1`;
CREATE TABLE `membership_detail_14` like `membership_detail_1`;
CREATE TABLE `membership_detail_15` like `membership_detail_1`;
CREATE TABLE `membership_detail_16` like `membership_detail_1`;
CREATE TABLE `membership_detail_17` like `membership_detail_1`;
CREATE TABLE `membership_detail_18` like `membership_detail_1`;
CREATE TABLE `membership_detail_19` like `membership_detail_1`;

CREATE TABLE `membership_detail_20` like `membership_detail_1`;
CREATE TABLE `membership_detail_21` like `membership_detail_1`;
CREATE TABLE `membership_detail_22` like `membership_detail_1`;
CREATE TABLE `membership_detail_23` like `membership_detail_1`;
CREATE TABLE `membership_detail_24` like `membership_detail_1`;
CREATE TABLE `membership_detail_25` like `membership_detail_1`;
CREATE TABLE `membership_detail_26` like `membership_detail_1`;
CREATE TABLE `membership_detail_27` like `membership_detail_1`;
CREATE TABLE `membership_detail_28` like `membership_detail_1`;
CREATE TABLE `membership_detail_29` like `membership_detail_1`;

CREATE TABLE `membership_detail_30` like `membership_detail_1`;
CREATE TABLE `membership_detail_31` like `membership_detail_1`;
CREATE TABLE `membership_detail_32` like `membership_detail_1`;
CREATE TABLE `membership_detail_33` like `membership_detail_1`;
CREATE TABLE `membership_detail_34` like `membership_detail_1`;
CREATE TABLE `membership_detail_35` like `membership_detail_1`;
CREATE TABLE `membership_detail_36` like `membership_detail_1`;
CREATE TABLE `membership_detail_37` like `membership_detail_1`;
CREATE TABLE `membership_detail_38` like `membership_detail_1`;
CREATE TABLE `membership_detail_39` like `membership_detail_1`;

CREATE TABLE `membership_detail_40` like `membership_detail_1`;
CREATE TABLE `membership_detail_41` like `membership_detail_1`;
CREATE TABLE `membership_detail_42` like `membership_detail_1`;
CREATE TABLE `membership_detail_43` like `membership_detail_1`;
CREATE TABLE `membership_detail_44` like `membership_detail_1`;
CREATE TABLE `membership_detail_45` like `membership_detail_1`;
CREATE TABLE `membership_detail_46` like `membership_detail_1`;
CREATE TABLE `membership_detail_47` like `membership_detail_1`;
CREATE TABLE `membership_detail_48` like `membership_detail_1`;
CREATE TABLE `membership_detail_49` like `membership_detail_1`;

CREATE TABLE `membership_detail_50` like `membership_detail_1`;
CREATE TABLE `membership_detail_51` like `membership_detail_1`;
CREATE TABLE `membership_detail_52` like `membership_detail_1`;
CREATE TABLE `membership_detail_53` like `membership_detail_1`;
CREATE TABLE `membership_detail_54` like `membership_detail_1`;
CREATE TABLE `membership_detail_55` like `membership_detail_1`;
CREATE TABLE `membership_detail_56` like `membership_detail_1`;
CREATE TABLE `membership_detail_57` like `membership_detail_1`;
CREATE TABLE `membership_detail_58` like `membership_detail_1`;
CREATE TABLE `membership_detail_59` like `membership_detail_1`;

CREATE TABLE `membership_detail_60` like `membership_detail_1`;
CREATE TABLE `membership_detail_61` like `membership_detail_1`;
CREATE TABLE `membership_detail_62` like `membership_detail_1`;
CREATE TABLE `membership_detail_63` like `membership_detail_1`;
CREATE TABLE `membership_detail_64` like `membership_detail_1`;


# 参考语句
SELECT * FROM membership_card WHERE card_code = "Card999";
UPDATE membership_card SET use_stock=1 WHERE card_code="Card999";

SELECT * FROM membership_privilege WHERE privilege_code = "GBT****";
SELECT * FROM membership_privilege WHERE association_code = "C";

SELECT * FROM card_sale_order_1 WHERE user_name="qiu" AND order_no= "cccc";
SELECT * FROM card_sale_order_1 WHERE user_name="qiu" AND card_code= "Card999";
SELECT * FROM card_sale_order_1 WHERE user_name="qiu" AND order_status= 1;

SELECT * FROM card_sale_pay_record_1 WHERE user_name="qiu" AND order_no= "cccc";
SELECT * FROM card_sale_pay_record_1 WHERE user_name="qiu" AND inner_trans_id= "cccc";

SELECT * FROM membership_account_1 WHERE user_name="qiu" AND beneficiary_card_no= "cccc" AND beneficiary_card_type=0;
SELECT * FROM membership_account_1 WHERE user_name="qiu" AND card_no= "cccc" ;

SELECT * FROM membership_info_1 WHERE user_name="qiu" AND card_no= "cccc" ;

SELECT * FROM membership_detail_1 WHERE user_name="qiu";
SELECT * FROM membership_detail_1 WHERE user_name="qiu" and flight_order_no="";

-- 会员卡
CREATE TABLE `membership_user_1`(
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `card_no` varchar(32) NOT NULL DEFAULT '' COMMENT '卡号',
  `status` INT(2) NOT NULL DEFAULT '0' comment '账户状态(0使用，1作废，2过期)',
  `degree` INT(2) NOT NULL DEFAULT '0' comment '会员等级',
  `begin_date` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '会员生效时间',
  `end_date` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '会员失效时间',
  `order_no` varchar(32) NOT NULL DEFAULT '' COMMENT '购买订单号,卡单号',
  `channel` varchar(32) NOT NULL DEFAULT '' COMMENT '开卡渠道',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' comment '创建时间',
  `last_update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_last_update_time` (`last_update_time`),
  UNIQUE KEY `uniq_user_name` (`user_name`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员卡信息';

CREATE TABLE `membership_user_2` like `membership_user_1`;
CREATE TABLE `membership_user_3` like `membership_user_1`;
CREATE TABLE `membership_user_4` like `membership_user_1`;


-- 退款记录
CREATE TABLE `card_refund_order_record_1` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `order_no` varchar(32) NOT NULL DEFAULT '' COMMENT '购买订单号,卡单号',
  `card_no` varchar(32) NOT NULL DEFAULT '' COMMENT '卡号',
  `sale_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '购卡金额',
  `refund_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '给用户退款金额',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '退卡状态',
  `share_data` varchar(200) NOT NULL DEFAULT '' COMMENT '分账公式',
  `merchant_code` varchar(20) NOT NULL DEFAULT '' COMMENT '商户号',
  `busi_type` varchar(20) NOT NULL DEFAULT '' COMMENT '业务系统编号',
  `inner_trans_id` varchar(50) NOT NULL DEFAULT '' COMMENT '支付中心退款交易流水号',
  `refund_no` varchar(50) NOT NULL DEFAULT '' COMMENT '退卡单号',
  `refund_apply_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '给用户退款发起时间',
  `refund_finish_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '给用户退款成功时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_card_no` (`card_no`),
  UNIQUE KEY `uniq_refund_no` (`refund_no`),
  KEY `idx_card_no` (`card_no`),
  KEY `idx_refund_no` (`refund_no`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_update_time` (`update_time`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='退卡记录';

CREATE TABLE `card_refund_order_record_2` like `card_refund_order_record_1`;
CREATE TABLE `card_refund_order_record_3` like `card_refund_order_record_1`;
CREATE TABLE `card_refund_order_record_4` like `card_refund_order_record_1`;

-- 绑定人表
CREATE TABLE `membership_beneficiary_1`(
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `order_no` varchar(255) NOT NULL DEFAULT '' COMMENT '订单号',
  `card_no` varchar(255) NOT NULL DEFAULT '' COMMENT '卡号',
  `user_name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `beneficiary_name` varchar(32) NOT NULL DEFAULT '' COMMENT ' 绑定受益人名称',
  `beneficiary_card_type` INT(2) NOT NULL DEFAULT '0' comment '绑定受益人证件类型',
  `beneficiary_card_no` varchar(128) NOT NULL DEFAULT '' COMMENT ' 绑定受益人证件号',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' comment '创建时间',
  `last_update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_card_no` (`card_no`),
  KEY `idx_order_no` (`order_no`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='特权用户账户信息表';

CREATE TABLE `membership_beneficiary_2` like `membership_beneficiary_1`;
CREATE TABLE `membership_beneficiary_3` like `membership_beneficiary_1`;
CREATE TABLE `membership_beneficiary_4` like `membership_beneficiary_1`;


-- 飞行记录表 8
CREATE TABLE `flight_record_1`(
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `flight_order_no` varchar(64) NOT NULL DEFAULT '' COMMENT '机票订单号',
  `passenger_key` INT(2) NOT NULL DEFAULT '0' comment '乘机人key',
  `flight_num` INT NOT NULL DEFAULT 0 COMMENT '飞行次数：乘机人数*航段数',
  `flight_create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' comment '机票订单创建时间',
  `flight_departure_date` DATE NOT NULL DEFAULT '1970-01-01' COMMENT '航班起飞日期',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' comment '创建时间',
  `last_update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY idx_user_name (`user_name`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_last_update_time` (`last_update_time`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='飞行记录表';

CREATE TABLE `flight_record_2` LIKE `flight_record_1`;
CREATE TABLE `flight_record_3` LIKE `flight_record_1`;
CREATE TABLE `flight_record_4` LIKE `flight_record_1`;
CREATE TABLE `flight_record_5` LIKE `flight_record_1`;
CREATE TABLE `flight_record_6` LIKE `flight_record_1`;
CREATE TABLE `flight_record_7` LIKE `flight_record_1`;
CREATE TABLE `flight_record_8` LIKE `flight_record_1`;

-- 兑礼记录 4
CREATE TABLE `flight_gift_exchange_1`(
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `card_no` varchar(32) NOT NULL DEFAULT '' COMMENT '会员卡号',
  `flight_gift_degree` INT NOT NULL DEFAULT 0 COMMENT '飞行礼物等级',
  `flight_num` INT NOT NULL DEFAULT 0 COMMENT '兑换的飞行次数',
  `gift_type` INT NOT NULL DEFAULT 0 COMMENT 'FlightGiftType礼物类型',
  `gift_name` varchar(64)  NOT NULL DEFAULT '' COMMENT '礼物名称',
  `gift_prize` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '兑礼金额',
  `gift_id` varchar(32) NOT NULL DEFAULT '' COMMENT '兑礼标识',
  `end_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '过期时间',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' comment '创建时间',
  `last_update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY idx_user_name (`user_name`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_last_update_time` (`last_update_time`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='兑礼记录表';

CREATE TABLE `flight_gift_exchange_2` LIKE `flight_gift_exchange_1`;
CREATE TABLE `flight_gift_exchange_3` LIKE `flight_gift_exchange_1`;
CREATE TABLE `flight_gift_exchange_4` LIKE `flight_gift_exchange_1`;

-- 权益发放纪录表（会员价，退改折扣）
CREATE TABLE `membership_grant_1`(
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `card_no` varchar(32) NOT NULL DEFAULT '' COMMENT '会员卡号',
  `privilege_type` INT(2) NOT NULL DEFAULT '0' comment '特权类型（0:X,1:代金券,2:专属服务)',
  `privilege_code` varchar(32) NOT NULL DEFAULT '' COMMENT '权益code',
  `discount_formula` varchar(32) NOT NULL DEFAULT '' COMMENT '优惠公式，用户计算优惠金额',
  `begin_date` DATE NOT NULL DEFAULT '1970-01-01' COMMENT '生效日期，当天有效',
  `end_date` DATE NOT NULL DEFAULT '1970-01-01' COMMENT '过期日期，当天有效',
  `next_grant_date` DATE NOT NULL DEFAULT '1970-01-01' COMMENT '下一次发放日期',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' comment '创建时间',
  `last_update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `total_num` INT NOT NULL DEFAULT '0' comment '本期权益总数',
  `limit_num` INT NOT NULL DEFAULT '0' COMMENT '本期剩余权益数',
  `issue_no` TINYINT(4) NOT NULL DEFAULT 1 COMMENT '权益发放期数',
  PRIMARY KEY (`id`),
  KEY idx_user_name (`user_name`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权益发放记录表';

CREATE TABLE membership_grant_2 LIKE membership_grant_1;
CREATE TABLE membership_grant_3 LIKE membership_grant_1;
CREATE TABLE membership_grant_4 LIKE membership_grant_1;
CREATE TABLE membership_grant_5 LIKE membership_grant_1;
CREATE TABLE membership_grant_6 LIKE membership_grant_1;
CREATE TABLE membership_grant_7 LIKE membership_grant_1;
CREATE TABLE membership_grant_8 LIKE membership_grant_1;

CREATE TABLE trial_gift_1(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  user_name VARCHAR(32) NOT NULL  DEFAULT '' COMMENT '用户名',
  status TINYINT NOT NULL DEFAULT 0 COMMENT '状态',
  gift_type TINYINT NOT NULL DEFAULT -1 COMMENT '礼物类型,-1未定义,0试用会员',
  card_no VARCHAR(32) NOT NULL DEFAULT '' COMMENT '试用会员卡号',
  create_time DATETIME NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  last_update_time DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '最后更新时间',
  PRIMARY KEY (id),
  INDEX idx_user_name(user_name),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_last_update_time` (`last_update_time`)
) CHARSET utf8mb4 ENGINE InnoDB COMMENT '促销礼物';
CREATE TABLE
  trial_gift_2 LIKE trial_gift_1;

CREATE TABLE `membership_privilege_ext`(
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `privilege_code` varchar(32) NOT NULL DEFAULT '' COMMENT '特权编码',
  `ext_key` varchar(64) NOT NULL DEFAULT '' COMMENT '扩展字段key',
  `ext_key_desc` varchar(64) NOT NULL DEFAULT '' COMMENT '扩展字段说明',
  `ext_value` varchar(1024) NOT NULL DEFAULT '' COMMENT '扩展字段value',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' comment '创建时间',
  `last_update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY idx_privilege_code (`privilege_code`),
  UNIQUE KEY `uniq_privilege_code_key` (`privilege_code`, `ext_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='特权信息扩展表';

CREATE TABLE `user_info_1`(
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_name` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `name` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `id_num` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '身份证号',
  `birth` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' comment '生日时间',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' comment '创建时间',
  `last_update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY idx_user_name (`user_name`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基础信息表';

CREATE TABLE `user_info_2` LIKE `user_info_1`;
CREATE TABLE `user_info_3` LIKE `user_info_1`;
CREATE TABLE `user_info_4` LIKE `user_info_1`;

CREATE TABLE `membership_express_record_1`(
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_name` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `name` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `phone` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '手机号',
  `address` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '邮寄地址',
  `goods_type` SMALLINT NOT NULL DEFAULT 0 COMMENT '邮寄商品类型',
  `privilege_type` SMALLINT NOT NULL DEFAULT 0 COMMENT '权益类型',
  `card_no` VARCHAR (255) NOT NULL DEFAULT '' COMMENT '会员卡号',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' comment '创建时间',
  `last_update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY idx_user_name (`user_name`),
  KEY idx_card_no(`card_no`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮寄信息记录表';

CREATE TABLE `member_invitation_bounty_record_1` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `order_no` varchar(32) NOT NULL DEFAULT '' COMMENT '订单号',
  `user_name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `acceptor_user_name` varchar(32) NOT NULL DEFAULT '' COMMENT '受邀用户名',
  `operation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `operation_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '操作类型1:已到账,2:提取中,3:提取失败,4:已收货,5:已退货',
  `operation_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '操作金额',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `bounty_record_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '活动类型0:多级邀请,1:试用会员赠送分享',
  PRIMARY KEY (`id`),
  KEY `idx_order_no` (`order_no`),
  KEY `idx_user_name` (`user_name`),
  KEY `idx_acceptor_user_name` (`acceptor_user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员邀请奖励金记录表';

CREATE TABLE `user_coupon_1`(
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_name` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `order_no` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '订单号',
  `coupon_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '券码',
  `coupon_status` TINYINT(2) NOT NULL DEFAULT -1 COMMENT '券码状态',
  `coupon_type` TINYINT(2) NOT NULL DEFAULT -1 COMMENT '券类型, 立减券,返现券等',
  `coupon_activity_type` TINYINT(2) NOT NULL DEFAULT -1 COMMENT '发券活动类型, 会员活动领券等',
  `coupon_source` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '券供应源,Flight, Holiday',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' comment '抵扣金额',
  `validate_start` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '有效开始日期',
  `validate_end` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '有效结束日期',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `last_update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_name_coupon_id` (`user_name`,`coupon_id`),
  KEY `idx_user_name_order_no` (`user_name`,`order_no`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_last_update_time` (`last_update_time`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员代金券表';

CREATE TABLE `user_coupon_2` like `user_coupon_1`;
CREATE TABLE `user_coupon_3` like `user_coupon_1`;
CREATE TABLE `user_coupon_4` like `user_coupon_1`;
CREATE TABLE `user_coupon_5` like `user_coupon_1`;
CREATE TABLE `user_coupon_6` like `user_coupon_1`;
CREATE TABLE `user_coupon_7` like `user_coupon_1`;
CREATE TABLE `user_coupon_8` like `user_coupon_1`;