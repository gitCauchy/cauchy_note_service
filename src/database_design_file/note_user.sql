SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for aac_user
-- ----------------------------
DROP TABLE IF EXISTS `note_user`;
CREATE TABLE `note_user`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id,自增',
  `user_name` char(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户名',
  `email` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '电子邮箱',
  `password` char(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '密码',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_non_expired` tinyint(2) UNSIGNED NOT NULL COMMENT '0 - 是，1 - 否',
  `is_non_locked` tinyint(2) UNSIGNED NOT NULL COMMENT '未锁定 0 - 是，1 - 否',
  `is_password_non_expired` tinyint(2) UNSIGNED NOT NULL COMMENT '密码状态 0 - 正常， 1 - 过期',
  `is_enable` tinyint(2) UNSIGNED NOT NULL COMMENT '账户是否可用 0 - 是，1 - 否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;