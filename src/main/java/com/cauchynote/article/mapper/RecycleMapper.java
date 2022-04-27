package com.cauchynote.article.mapper;

import com.cauchynote.article.entity.Article;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 回收站持久层
 *
 * @Author lingling
 * @Date 2022/4/23
 */
@Repository
public interface RecycleMapper {
    /**
     * 获取所有逻辑删除文章
     *
     * @param authorId 作者ID
     * @param pageSize 页大小
     * @param startNum 开始位置
     * @param keyword  搜索关键词
     * @return List<Article>
     */
    @Select("select id, title, author_id, content,create_time,modify_time,status from note_article where author_id = " +
        "#{authorId} and status = 1 and title like '%${keyword}%' limit #{startNum}, #{pageSize}")
    @Results(id = "getAllDelete", value = {
        @Result(column = "id", property = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
        @Result(column = "title", property = "title", javaType = String.class, jdbcType = JdbcType.VARCHAR),
        @Result(column = "author_id", property = "authorId", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
        @Result(column = "content", property = "content", javaType = String.class, jdbcType = JdbcType.LONGVARCHAR),
        @Result(column = "create_time", property = "createTime", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
        @Result(column = "modify_time", property = "modifyTime", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
        @Result(column = "status", property = "status", javaType = Integer.class, jdbcType = JdbcType.TINYINT)})
    List<Article> getDeleteArticleList(Integer authorId, Integer pageSize, Integer startNum, String keyword);

    /**
     * 获取已经逻辑删除的文章的总数
     *
     * @param authorId 作者ID
     * @param keyword  搜索关键词
     * @return 数量
     */
    @Select("select count(id) from note_article where author_id = #{authorId} and title like '%${keyword}%' and " +
        "status = 1")
    Integer getDeleteArticleTotal(Integer authorId, String keyword);

    /**
     * 根据文章id彻底删除（物理删除文章）
     *
     * @param id 文章ID
     * @return 数据变更数量
     */
    @Delete("delete  from  note_article where id=#{id}")
    int deleteArticleByIdPhysical(Integer id);

    /**
     * 恢复文章
     *
     * @param id 文章id
     * @return 变更数量
     */
    @Update("update note_article set status = 0 where id = #{id}")
    int restoreArticleById(Integer id);

    /**
     * 一键清空回收站里面所有文章
     *
     * @param authorId 作者ID
     * @return 更新数量
     */
    @Delete("delete  from  note_article where author_id=#{authorId} and status=1")
    int deleteAllArticlePhysical(Integer authorId);

    /**
     * 一键恢复回收站里所有文章
     *
     * @param authorId 作者ID
     * @return 变更数量
     */
    @Update("update note_article set status = 0 where author_id=#{authorId} and status=1")
    int restoreAllArticle(Integer authorId);

}
