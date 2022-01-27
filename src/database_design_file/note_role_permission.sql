SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for note_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `note_role_permission`;
CREATE TABLE `note_role_permission`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `role_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;