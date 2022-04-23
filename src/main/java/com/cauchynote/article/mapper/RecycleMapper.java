package com.cauchynote.article.mapper;
import com.cauchynote.article.entity.Article;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

/**
 * @Author lingling
 * @Description
 * @Date 2022/4/23
 * @Version
 */
@Repository
public interface RecycleMapper {
    /**
     * 获取所有逻辑删除文章
     * @param authorId
     * @param pageSize
     * @param startNum
     * @param keyword
     * @return
     */

    @Select("select id, title, author_id, content,create_time,modify_time,status from note_article where author_id = " +
        "#{authorId} and status = 1 and title like '%${keyword}%' limit #{startNum}, #{pageSize}")
    @Results(id = "getAllDelete", value = {
        @Result(column = "id", property = "id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
        @Result(column = "title", property = "title", javaType = String.class, jdbcType = JdbcType.VARCHAR),
        @Result(column = "author_id", property = "authorId", javaType = Long.class, jdbcType = JdbcType.BIGINT),
        @Result(column = "content", property = "content", javaType = String.class, jdbcType = JdbcType.LONGVARCHAR),
        @Result(column = "create_time", property = "createTime", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
        @Result(column = "modify_time", property = "modifyTime", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
        @Result(column = "status", property = "status", javaType = Integer.class, jdbcType = JdbcType.TINYINT)})
    List<Article> getDeleteArticleList(Long authorId, Integer pageSize, Integer startNum, String keyword);

    /**
     * 获取已经逻辑删除的文章的总数
     * @param authorId
     * @param keyword
     * @return
     */
    @Select("select count(id) from note_article where author_id = #{authorId} and title like '%${keyword}%' and " +
        "status = 1")
    Integer getDeleteArticleTotal(Long authorId, String keyword);
    /**
     * 根据文章id彻底删除（物理删除文章）
     * @param Id
     * @return
     */
    @Delete("delete  from  note_article where id=#{id}")
    int deleteArticleByIdPhysical(Long Id);

    /**
     * 恢复文章
     * @param id
     * @return
     */

    @Update("update note_article set status = 0 where id = #{id}")
    int RestoreArticleById(Long id);

    /**
     * 一键清空回收站里面所有文章
     * @param authorId
     * @return
     */
    @Delete("delete  from  note_article where author_id=#{authorId} and status=1")
    int deleteAllArticlePhysical(Long authorId);

    /**
     * 一键恢复回收站里所有文章
     * @param authorId
     * @return
     */

    @Update("update note_article set status = 0 where author_id=#{authorId} and status=1")
    int RestoreAllArticle(Long authorId);

}
