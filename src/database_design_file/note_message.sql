SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for note_message
-- ----------------------------
DROP TABLE IF EXISTS `note_message`;
CREATE TABLE `note_message`
(
    `id`           int(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id、自增、无符号',
    `sender_id`    int(20) NOT NULL COMMENT '消息发送人id',
    `receiver_id`  int(20) NOT NULL COMMENT '消息接收人id',
    `message_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '消息内容',
    `message_type` tinyint(255) NOT NULL COMMENT '消息类型 0 – 好友请求 1 – 笔记分享 2 – 好友请求反馈消息类型 0 – 好友请求 1 – 笔记分享 2 – 好友请求反馈',
    `send_date`    timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '发送日期',
    `status`       tinyint(255) NOT NULL COMMENT '消息状态 0 – 未读|1-已读|2-删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX          `message_idx_receiver_id`(`receiver_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;
