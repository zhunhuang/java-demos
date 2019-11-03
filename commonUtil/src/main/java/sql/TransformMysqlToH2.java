package sql;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 转换navicat导出的mysql的建表语句为h2的语法
 *
 * 主要的要注意的点是:
 *
 * 1.设置H2为mysql模式, 可以通过 SET MODE MYSQL;语句来实现
 *
 * 2.'`'全部要去掉
 *
 * 3.字段的字符集设置'COLLATE utf8_bin'不支持, 需要删除, 如这样的'`operator` varchar(10) COLLATE utf8_bin NOT NULL'
 *
 * 4.注释按道理也没问题的, 但是没有用, 所以删除了.
 *
 * 5.'ENGINE=InnoDB'设置不支持, 删掉
 *
 * 6.'DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP'不支持, 修改为H2类似的'AS CURRENT_TIMESTAMP'
 *
 * 7.H2的索引名必须要全局唯一, 所以需要替换所有的索引名为全局唯一
 *
 * @author tudesheng
 * @since 2016年6月20日 下午8:37:52
 *
 */
public class TransformMysqlToH2 {

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\haogrgr\\Desktop\\你的sql文件");
        String content = "CREATE TABLE `fee_detail_0` (\n" +
                "  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,\n" +
                "  `fee_detail_no` bigint(20) NOT NULL COMMENT '计费单号',\n" +
                "  `business_party_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '业务方id',\n" +
                "  `fee_scene` int(11) NOT NULL DEFAULT '0' COMMENT '计费场景',\n" +
                "  `out_no` varchar(64) NOT NULL COMMENT '外部唯一单号',\n" +
                "  `product_id` bigint(20) DEFAULT NULL COMMENT '产品Id',\n" +
                "  `fee_subject_type` int(11) NOT NULL DEFAULT '0' COMMENT '计费对象类型',\n" +
                "  `fee_subject_id` varchar(64) NOT NULL DEFAULT '0' COMMENT '计费对象Id',\n" +
                "  `fee_amount` decimal(15,2) DEFAULT NULL COMMENT '计费金额',\n" +
                "  `currency` tinyint(4) DEFAULT '0' COMMENT '货币类型, 0人民币',\n" +
                "  `fee_cycle` tinyint(4) DEFAULT NULL COMMENT '计费周期',\n" +
                "  `fee_mode` tinyint(4) DEFAULT NULL COMMENT '计费模式',\n" +
                "  `fee_type` tinyint(4) DEFAULT NULL COMMENT '计费类型',\n" +
                "  `fee_direction` tinyint(4) DEFAULT NULL COMMENT '费用方向, 1收款, 2付款',\n" +
                "  `fee_rule_detail` varchar(1024) DEFAULT NULL COMMENT '计费规则详细内容(存储差异化的计费规则), json格式',\n" +
                "  `fee_rate` decimal(10,6) DEFAULT NULL COMMENT '费率',\n" +
                "  `trade_no` varchar(64) DEFAULT NULL COMMENT '交易单号',\n" +
                "  `refund_no` varchar(64) DEFAULT NULL COMMENT '退款单号',\n" +
                "  `trade_amount` decimal(20,0) DEFAULT NULL COMMENT '交易金额',\n" +
                "  `trade_type` int(11) DEFAULT NULL COMMENT '交易类型 0支付 1撤销 2退款 3冲正',\n" +
                "  `ext` varchar(1024) DEFAULT NULL COMMENT '扩展字段1, json格式',\n" +
                "  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '计费明细状态',\n" +
                "  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
                "  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE KEY `uniq_out_no_fee_scene` (`out_no`,`fee_scene`),\n" +
                "  UNIQUE KEY `uniq_fee_detail_no` (`fee_detail_no`),\n" +
                "  UNIQUE KEY `uniq_trade_no_fee_scene` (`trade_no`,`refund_no`,`fee_scene`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8mb4 COMMENT='计费明细';";

        content = "SET MODE MYSQL;\n\n" + content;

        content = content.replaceAll("`", "");
        content = content.replaceAll("COLLATE.*(?=D)", "");
        content = content.replaceAll("COMMENT.*'(?=,)", "");
        content = content.replaceAll("\\).*ENGINE.*(?=;)", ")");
        content = content.replaceAll("DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", " AS CURRENT_TIMESTAMP");

        content = uniqueKey(content);

        System.out.println(content);
    }

    /**
     * h2的索引名必须全局唯一
     *
     * @param content sql建表脚本
     * @return 替换索引名为全局唯一
     */
    private static String uniqueKey(String content) {
        int inc = 0;
        Pattern pattern = Pattern.compile("(?<=KEY )(.*?)(?= \\()");
        Matcher matcher = pattern.matcher(content);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group() + inc++);
        }
        matcher.appendTail(sb);
        content = sb.toString();
        return content;
    }

}
