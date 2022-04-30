SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for note_article
-- ----------------------------
DROP TABLE IF EXISTS `note_article`;
CREATE TABLE `note_article`
(
    `id`          int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键 ID，自增，无符号',
    `title`       varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
    `content`     text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '文章内容',
    `author_id`   int(11) NOT NULL COMMENT '作者 ID',
    `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
    `modify_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
    `status`      tinyint(3) UNSIGNED NOT NULL COMMENT '文章状态 0 - 正常 | 1- 删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `article_idx_title`(`title`) USING BTREE,
    INDEX         `article_idx_author_id`(`author_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;