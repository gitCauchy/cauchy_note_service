package com.cauchynote.share.mapper;

import com.cauchynote.share.entity.SharedArticle;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
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
     * @return List
     */
    @Select("select note_article_share.id, user_name, share_user_id, receive_user_id, article_id, share_date, " +
        "valid_day, is_revisable, title, content, note_article.create_time, modify_time ,status from " +
        "note_article_share left join note_article on note_article_share.article_id left join note_user on " +
        "note_article_share.share_user_id = note_user.id = note_article.id where receive_user_id = #{receiverId}")
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
    List<SharedArticle> getSharedArticleList(Long receiverId);
}
