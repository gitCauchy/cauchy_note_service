SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for note_permission
-- ----------------------------
DROP TABLE IF EXISTS `note_permission`;
CREATE TABLE `note_permission`
(
    `id`              int(255) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '权限 id，自增，无符号',
    `permission_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '权限名称',
    `permission_tag`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '权限标签',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

SET
FOREIGN_KEY_CHECKS = 1;
