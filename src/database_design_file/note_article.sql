-- ----------------------------
-- Table structure for note_article
-- ----------------------------
DROP TABLE IF EXISTS `note_article`;
CREATE TABLE `note_article`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `author_id` bigint(20) NOT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `status` tinyint(255) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;