
/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`springbootsecurityjwt1` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `springbootsecurityjwt1`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
                      `uid` bigint(20) NOT NULL auto_increment COMMENT '自增长ID',
                      `open_id` varchar(16) DEFAULT NULL COMMENT '微信openid',
                      `nick_name` varchar(16) DEFAULT NULL COMMENT '昵称',
                      `password` varchar(16) DEFAULT NULL COMMENT '密码',
                      `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
                      `roles` varchar(64) DEFAULT NULL COMMENT '角色',
                      `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last_update time',
                      `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'data create time',
                      PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
