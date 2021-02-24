package sql;

import file.FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * description:
 *
 * @author zhunhuang, 2020/6/1
 */
public class DateTableSqlGenerator {

    public static void main(String[] args) {
        String sql = "CREATE TABLE `fee_voucher_#date#` (\n" +
                "  `voucher_id` bigint(20) unsigned NOT NULL,\n" +
                "  `caller_id` bigint(20) NOT NULL COMMENT '业务方id',\n" +
                "  `biz_no` varchar(64) NOT NULL DEFAULT '' COMMENT '业务方单号，用于幂等',\n" +
                "  `event_time` bigint(20) DEFAULT NULL COMMENT '计费事件时间',\n" +
                "  `event_day` int(11) DEFAULT NULL COMMENT '事件日',\n" +
                "  `fee_subject_id` bigint(20) DEFAULT NULL COMMENT '计费对象',\n" +
                "  `biz_identify_no` bigint(20) DEFAULT NULL COMMENT '交易通知的业务识别码',\n" +
                "  `payout_merchant_id` bigint(20) DEFAULT NULL COMMENT '收费商户编号',\n" +
                "  `product_code` varchar(64) DEFAULT NULL COMMENT '产品编码',\n" +
                "  `service_id` int(11) DEFAULT NULL COMMENT '服务类型',\n" +
                "  `scene_name` varchar(64) DEFAULT NULL COMMENT '场景名',\n" +
                "  `trade_serial_no` varchar(64) DEFAULT NULL COMMENT '交易单号',\n" +
                "  `biz_serial_no` varchar(64) DEFAULT NULL COMMENT '业务单号',\n" +
                "  `out_no` varchar(64) DEFAULT NULL COMMENT '交易调用下游单号，非必填，财务对账使用',\n" +
                "  `trade_amount` decimal(20,4) DEFAULT NULL COMMENT '以分为单位',\n" +
                "  `trade_time` bigint(20) DEFAULT NULL COMMENT '交易时间',\n" +
                "  `voucher_fee_type` bigint(20) DEFAULT NULL COMMENT '计费类型，1参与计费，2不参与计费',\n" +
                "  `ext1` varchar(1024) DEFAULT NULL COMMENT '扩展字段1,保存原交易信息',\n" +
                "  `send_status` int(11) DEFAULT NULL COMMENT '发送状态 0未发送，1发送完成',\n" +
                "  `sub_flag` int(11) DEFAULT '-1' COMMENT '主子单标识-1默认，0主单，>0子单',\n" +
                "  `sub_trade_serial_no` varchar(128) DEFAULT NULL COMMENT '子单单号',\n" +
                "  `from_merchant_id` bigint(20) DEFAULT NULL COMMENT '转出id',\n" +
                "  `to_merchant_id` bigint(20) DEFAULT NULL COMMENT '转入id',\n" +
                "  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
                "  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',\n" +
                "  PRIMARY KEY (`voucher_id`),\n" +
                "  UNIQUE KEY `bizno_callerid_subflag_u_idx` (`biz_no`,`caller_id`,`sub_flag`),\n" +
                "  KEY `voucher_fee_type_send_time_idx` (`voucher_fee_type`,`send_status`,`event_time`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPRESSED KEY_BLOCK_SIZE=8 COMMENT='计费凭证表';";

//        sql = "alter table clear_record_#date# add column `clear_states` bigint(20) not null default 0 after `status`;\n";
//        generateByDate("20210101","20240101",sql);

        generateByInt(900,1000, sql);
    }

    public static void generateByDate(String startDay, String endDay, String sqlDemo) {
        StringBuilder sql = new StringBuilder();
        try {
            Date start = DateUtils.parseDate(startDay, "yyyyMMdd");
            Date end = DateUtils.parseDate(endDay, "yyyyMMdd");
            while (start.getTime() < end.getTime()) {
                sql.append("\n").append(replace(DateFormatUtils.format(start, "yyyyMMdd"), sqlDemo));
                start = DateUtils.addDays(start,1);
            }
            System.out.println(sql);
            FileUtils.write(new File("./sql-" + startDay + "-" + endDay + ".sql"), sql, "utf-8");
        } catch (ParseException | IOException e) {
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
