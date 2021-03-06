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
    int addArticleShare(Integer shareUserId, Integer receiveUserId, Integer articleId, Date shareDate, int validDay,
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
        "note_article.create_time, note_article.modify_time, note_article.status, note_friend_remark.remark_name from" +
        " note_article_share left join note_article on note_article_share.article_id = note_article.id " +
        "left join note_user on note_article_share.share_user_id = note_user.id left join note_friend_remark on " +
        "note_article_share.share_user_id = note_friend_remark.friend_id and note_article_share.receive_user_id " +
        "= note_friend_remark.user_id WHERE receive_user_id = #{receiverId} and note_article" +
        ".status = 0 and note_article.title like '%${keyword}%' limit #{startNum}, #{pageSize}")
    List<Map<String, Object>> getSharedArticleList(Integer receiverId, Integer pageSize, Integer startNum, String keyword);

    /**
     * 删除分享
     *
     * @param id share_id
     * @return 删除记录的条目数
     */
    @Delete("delete from note_article_share where id = #{id}")
    Integer deleteArticleShare(Integer id);

    /**
     * 获取笔记被分享的次数
     *
     * @param shareUserId   分享人 ID
     * @param receiveUserId 接收人 ID
     * @param articleId     文章 ID
     * @return 同一笔记分享列表
     */
    @Select("select share_user_id, receive_user_id, article_id, share_date, valid_day, is_revisable from " +
        "note_article_share where share_user_id = #{shareUserId} and receive_user_id = #{receiveUserId} and " +
        "article_id = #{articleId}")
    @Results(id = "shareResultMap", value = {
        @Result(column = "id", property = "shareId", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
        @Result(column = "share_user_id", property = "shareUserId", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
        @Result(column = "receive_user_id", property = "receiverId", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
        @Result(column = "article_id", property = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
        @Result(column = "share_date", property = "shareDate", javaType = Date.class, jdbcType = JdbcType.DATE),
        @Result(column = "valid_day", property = "validDay", javaType = Integer.class, jdbcType = JdbcType.TINYINT),
        @Result(column = "is_revisable", property = "isRevisable", javaType = Integer.class, jdbcType = JdbcType.TINYINT)
    })
    List<SharedArticle> getArticleShareCount(Integer shareUserId, Integer receiveUserId, Integer articleId);
}
