SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for note_profile
-- ----------------------------
DROP TABLE IF EXISTS `note_profile`;
CREATE TABLE `note_profile`
(
    `user_id`   int(10) UNSIGNED NOT NULL COMMENT '用户 id',
    `nick_name` char(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '昵称',
    `birthday`  timestamp(0) NULL DEFAULT NULL COMMENT '生日',
    `gender`    tinyint(4) NULL DEFAULT NULL COMMENT '性别',
    `photo`     blob NULL COMMENT '头像',
    `telephone` char(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '电话',
    `address`   char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '地址',
    PRIMARY KEY (`user_id`) USING BTREE,
    UNIQUE INDEX `profile_idex_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;