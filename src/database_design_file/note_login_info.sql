SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for note_login_info
-- ----------------------------
DROP TABLE IF EXISTS `note_login_info`;
CREATE TABLE `note_login_info`
(
    `id`         int(20) NOT NULL AUTO_INCREMENT COMMENT '主键id、自增、无符号',
    `user_id`    int(20) NOT NULL COMMENT '用户id',
    `login_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '登录时间',
    `ip`         char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP 地址',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX        `login_info_idx_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 233 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;

