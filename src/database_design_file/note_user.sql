SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for note_user
-- ----------------------------
DROP TABLE IF EXISTS `note_user`;
CREATE TABLE `note_user`
(
    `id`                      int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键 id，自增，无符号',
    `user_name`               char(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL COMMENT '用户名',
    `email`                   char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL COMMENT '邮箱',
    `password`                char(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
    `create_time`             timestamp(0)                                        NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '创建时间',
    `is_non_expired`          tinyint(2) UNSIGNED NOT NULL COMMENT '未过期 0 - 是 | 1 - 否',
    `is_non_locked`           tinyint(2) UNSIGNED NOT NULL COMMENT '未锁定 0 - 是 | 1 - 否',
    `is_password_non_expired` tinyint(2) UNSIGNED NOT NULL COMMENT '密码未过期 0 - 正常， 1 - 过期',
    `is_enable`               tinyint(2) UNSIGNED NOT NULL COMMENT '是否有效 0 - 是 | 1 - 否',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `user_idx_username`(`user_name`) USING BTREE COMMENT 'username_索引'
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

SET
FOREIGN_KEY_CHECKS = 1;
