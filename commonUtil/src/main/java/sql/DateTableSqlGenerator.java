package sql;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * description:
 *
 * @author zhunhuang, 2020/6/1
 */
public class DateTableSqlGenerator {

    public static void main(String[] args) {
        String sql = "CREATE TABLE `fee_detail_#date#` (\n" +
                "  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,\n" +
                "  `fee_detail_no` bigint(20) NOT NULL COMMENT '计费单号',\n" +
                "  `business_party_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '业务方id',\n" +
                "  `fee_scene` int(11) NOT NULL DEFAULT '0' COMMENT '计费场景',\n" +
                "  `out_no` varchar(64) NOT NULL COMMENT '外部唯一单号',\n" +
                "  `product_id` varchar(64) DEFAULT NULL COMMENT '产品Id',\n" +
                "  `fee_subject_type` int(11) NOT NULL DEFAULT '0' COMMENT '计费对象类型',\n" +
                "  `fee_subject_id` varchar(64) NOT NULL DEFAULT '0' COMMENT '计费对象Id',\n" +
                "  `fee_amount` decimal(15,2) DEFAULT NULL COMMENT '计费金额',\n" +
                "  `currency` tinyint(4) DEFAULT '0' COMMENT '货币类型, 0人民币',\n" +
                "  `fee_cycle` tinyint(4) DEFAULT NULL COMMENT '计费周期',\n" +
                "  `fee_mode` tinyint(4) DEFAULT NULL COMMENT '计费模式',\n" +
                "  `fee_type` tinyint(4) DEFAULT NULL COMMENT '计费类型',\n" +
                "  `fee_money_type` int(11) NOT NULL DEFAULT '0' COMMENT '费用类型',\n" +
                "  `fee_direction` tinyint(4) DEFAULT NULL COMMENT '费用方向, 1收款, 2付款',\n" +
                "  `fee_rule_detail` varchar(1024) DEFAULT NULL COMMENT '计费规则详细内容(存储差异化的计费规则), json格式',\n" +
                "  `fee_rate` decimal(10,6) DEFAULT NULL COMMENT '费率',\n" +
                "  `trade_no` varchar(128) DEFAULT NULL COMMENT '交易单号',\n" +
                "  `refund_no` varchar(128) DEFAULT NULL COMMENT '退款单号',\n" +
                "  `trade_amount` decimal(20,0) DEFAULT NULL COMMENT '交易金额',\n" +
                "  `trade_type` int(11) DEFAULT NULL COMMENT '交易类型 0支付 1撤销 2退款 3冲正',\n" +
                "  `event_day` int(11) DEFAULT NULL COMMENT '事件日',\n" +
                "  `event_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '计费事件发生时间',\n" +
                "  `ext` varchar(1024) DEFAULT NULL COMMENT '扩展字段1, json格式',\n" +
                "  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '计费明细状态',\n" +
                "  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
                "  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE KEY `uniq_out_no_fee_scene` (`out_no`,`fee_scene`),\n" +
                "  UNIQUE KEY `uniq_fee_detail_no` (`fee_detail_no`),\n" +
                "  UNIQUE KEY `uniq_trade_no_scene_money_type` (`trade_no`,`refund_no`,`fee_scene`,`fee_money_type`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='结算计费明细表';";

//        sql = "alter table clear_record_#date# add column `clear_states` bigint(20) not null default 0 after `status`;\n";
        generateByDate("20200101","20210101",sql);
        // 本次，新增了个bug

//        generateByInt(1,1000, sql);
    }

    public static void generateByDate(String startDay, String endDay, String sqlDemo) {
        try {
            Date start = DateUtils.parseDate(startDay, "yyyyMMdd");
            Date end = DateUtils.parseDate(endDay, "yyyyMMdd");
            while (start.getTime() < end.getTime()) {
                System.out.println(replace(DateFormatUtils.format(start,"yyyyMMdd"),sqlDemo));
                start = DateUtils.addDays(start,1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void generateByInt( int startIn, int endExclude, String sqlDemo) {
        for (int i = startIn; i < endExclude; i++) {
            System.out.println(replace(String.valueOf(i),sqlDemo));
        }
    }


    public static String replace(String date, String sql) {

        return sql.replaceFirst("#date#",date);
    }
}
