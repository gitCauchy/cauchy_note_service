package com.cauchynote.share.mapper;

import com.cauchynote.share.entity.SharedArticle;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 分享持久层
 *
 * @author Cauchy
 */
@Repository
public interface ShareMapper {

    /**
     * 添加文章分享
     *
     * @param shareUserId   分享者 ID
     * @param receiveUserId 接受者 ID
     * @param articleId     文章 ID
     * @param shareDate     分享日期
     * @param validDay      分享有效期
     * @param isRevisable   是否可修改
     * @return 插入数据条数
     */
    @Insert("insert into note_article_share (share_user_id, receive_user_id, article_id, share_date, valid_day, " +
        "is_revisable) values(#{shareUserId}, #{receiveUserId}, #{articleId}, #{shareDate}, #{validDay}, " +
        "#{isRevisable})")
    int addArticleShare(Long shareUserId, Long receiveUserId, Long articleId, Date shareDate, int validDay,
                        int isRevisable);

    /**
     * 获取其他用户分享给该用户的文章
     *
     * @param receiverId 接收者 ID
     * @param pageSize   页大小
     * @param startNum   开始位置
     * @param keyword    搜索关键词
     * @return List
     */
    @Select("SELECT note_article_share.id, note_user.user_name, note_article_share.share_user_id, " +
        "note_article_share.receive_user_id, note_article_share.article_id, note_article_share.share_date, " +
        "note_article_share.valid_day, note_article_share.is_revisable, note_article.title, note_article.content," +
        "note_article.create_time, note_article.modify_time, note_article.status FROM note_article_share LEFT JOIN " +
        "note_article ON note_article_share.article_id = note_article.id LEFT JOIN note_user ON " +
        "note_article_share.share_user_id = note_user.id WHERE receive_user_id = #{receiverId} and note_article" +
        ".status = 0 and note_article.title like '%${keyword}%' limit #{startNum}, #{pageSize}")
    @Results(id = "getSharedArticleList", value = {
        @Result(column = "id", property = "shareId", javaType = Long.class, jdbcType = JdbcType.BIGINT),
        @Result(column = "share_user_id", property = "shareUserId", javaType = Long.class, jdbcType = JdbcType.BIGINT),
        @Result(column = "receive_user_id", property = "receiverId", javaType = Long.class, jdbcType = JdbcType.BIGINT),
        @Result(column = "article_id", property = "id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
        @Result(column = "share_date", property = "shareDate", javaType = Date.class, jdbcType = JdbcType.DATE),
        @Result(column = "valid_day", property = "validDay", javaType = Integer.class, jdbcType = JdbcType.TINYINT),
        @Result(column = "is_revisable", property = "isRevisable", javaType = Integer.class, jdbcType = JdbcType.TINYINT),
        @Result(column = "title", property = "title", javaType = String.class, jdbcType = JdbcType.VARCHAR),
        @Result(column = "content", property = "content", javaType = String.class, jdbcType = JdbcType.LONGVARCHAR),
        @Result(column = "create_time", property = "createTime", javaType = Date.class, jdbcType = JdbcType.DATE),
        @Result(column = "modify_time", property = "modifyTime", javaType = Date.class, jdbcType = JdbcType.DATE),
        @Result(column = "status", property = "status", javaType = Integer.class, jdbcType = JdbcType.TINYINT),
        @Result(column = "user_name", property = "shareUsername", javaType = String.class, jdbcType = JdbcType.VARCHAR)
    })
    List<SharedArticle> getSharedArticleList(Long receiverId, Integer pageSize, Integer startNum, String keyword);

    /**
     * 删除分享
     *
     * @param id share_id
     * @return 删除记录的条目数
     */
    @Delete("delete from note_article_share where id = #{id}")
    int deleteArticleShare(Long id);
}
