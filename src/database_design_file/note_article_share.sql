SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for note_article_share
-- ----------------------------
DROP TABLE IF EXISTS `note_article_share`;
CREATE TABLE `note_article_share`
(
    `id`              int(20) NOT NULL AUTO_INCREMENT COMMENT '主键id、自增、无符号',
    `share_user_id`   int(20) NOT NULL COMMENT '分享用户 id',
    `receive_user_id` int(20) NOT NULL COMMENT '接收用户 id',
    `article_id`      int(20) NOT NULL COMMENT '文章 id',
    `share_date`      timestamp(0) NULL DEFAULT NULL COMMENT '分享日期',
    `valid_day`       tinyint(11) NULL DEFAULT NULL COMMENT '有效天数',
    `is_revisable`    tinyint(4) NULL DEFAULT NULL COMMENT '是否可修改 0 – 是| 1- 否 ',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX             `article_share_idx_receiver_user_id`(`receive_user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;
