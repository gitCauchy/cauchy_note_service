SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for note_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `note_role_menu`;
CREATE TABLE `note_role_menu`
(
    `id`      int(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id、自增、无符号',
    `role_id` int(20) UNSIGNED NOT NULL COMMENT '角色id',
    `menu_id` int(20) UNSIGNED NOT NULL COMMENT '菜单id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;

