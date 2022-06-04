SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for note_setting
-- ----------------------------
DROP TABLE IF EXISTS `note_setting`;
CREATE TABLE `note_setting`
(
    `user_id` int(10) UNSIGNED NOT NULL COMMENT '用户 id',
    `theme`   tinyint(255) NOT NULL DEFAULT 0 COMMENT '主题（默认为0）',
    `skin`    tinyint(255) NOT NULL DEFAULT 0 COMMENT '皮肤(默认为0）',
    PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;
