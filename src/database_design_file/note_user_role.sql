SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for note_user_role
-- ----------------------------
DROP TABLE IF EXISTS `note_user_role`;
CREATE TABLE `note_user_role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `user_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;