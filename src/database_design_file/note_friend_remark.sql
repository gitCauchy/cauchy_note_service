SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for note_friend_remark
-- ----------------------------
DROP TABLE IF EXISTS `note_friend_remark`;
CREATE TABLE `note_friend_remark`
(
    `id`          int(255) UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id`     int(11) NOT NULL COMMENT '用户 id',
    `friend_id`   int(11) NOT NULL COMMENT '好友 id',
    `remark_name` char(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注名',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `remark_idx_user_id_friend_id`(`user_id`, `friend_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;
