SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for note_friend
-- ----------------------------
DROP TABLE IF EXISTS `note_friend`;
CREATE TABLE `note_friend`
(
    `id`                 int(20) NOT NULL AUTO_INCREMENT COMMENT '主键id、自增、无符号',
    `user_id`            int(20) NOT NULL COMMENT '用户id',
    `friend_request_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '好友请求id列表',
    `friend_ids`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '好友id列表',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `friend_idx_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;
