package com.cauchynote.article.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import com.cauchynote.article.entity.Article;

/**
 * @author Cauchy
 * @ClassName ArticleMapper.java
 * @Date 2019年12月12日
 * @Description 文章持久层
 * @Version V0.1
 */
@Repository
public interface ArticleMapper {
    @Insert("insert into note_article(title, content, author_id, create_time, modify_time, status) values(#{title}, " +
            "#{content}, #{authorId}, now(), now(), #{status})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int addArticle(Article article);

    /**
     * @param id 文章 ID
     * @return 变更条数
     */
    @Update("update note_article set status = 1 where id = #{id}")
    int deleteArticle(Integer id);

    /**
     * 更新文章
     *
     * @param article 文章对象
     * @return 变更数量
     */
    @Update("update note_article set title = #{title}, modify_time = now(), content = #{content} where id = #{id}")
    int modifyArticle(Article article);

    /**
     * 获取文章信息
     *
     * @param id 文章ID
     * @return 文章对象
     */
    @Select("select id, title, content, author_id, create_time, status from note_article where id = #{id}")
    @Results(id = "getArticleDetailInformation", value = {
            @Result(column = "id", property = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(column = "title", property = "title", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "content", property = "content", javaType = String.class, jdbcType = JdbcType.LONGNVARCHAR),
            @Result(column = "author_id", property = "authorId", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", javaType = Integer.class, jdbcType = JdbcType.INTEGER)})
    Article getArticle(Integer id);

    /**
     * 获取文章列表
     *
     * @param authorId 作者ID
     * @param pageSize 页大小
     * @param startNum 开始位置
     * @param keyword  查询关键词
     * @return List
     */
    @Select("select id, title, author_id, content,create_time,modify_time,status from note_article where author_id = " +
            "#{authorId} and status = 0 and title like '%${keyword}%' limit #{startNum}, #{pageSize}")
    @Results(id = "getAll", value = {
            @Result(column = "id", property = "id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(column = "title", property = "title", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "author_id", property = "authorId", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(column = "content", property = "content", javaType = String.class, jdbcType = JdbcType.LONGVARCHAR),
            @Result(column = "create_time", property = "createTime", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modify_time", property = "modifyTime", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", javaType = Integer.class, jdbcType = JdbcType.TINYINT)})
    List<Article> getArticleList(Long authorId, Integer pageSize, Integer startNum, String keyword);

    /**
     * 获取文章数量
     *
     * @param authorId 作者ID
     * @param keyword  关键词
     * @return 文章数
     */
    @Select("select count(id) from note_article where author_id = #{authorId} and title like '%${keyword}%'")
    Integer getArticleTotal(Long authorId, String keyword);

    @Select("select count(*) from note_article where author_id = #{authorId} " +
            "and create_time >= #{startDate} and create_time <= #{endDate}")
    int getUserArticleCount(Long authorId, Date startDate, Date endDate);

    @Select("select count(*) from note_article where create_time > #{startDate}")
    int getTotalArticleCount(Date startDate);

    @Select("select author_id, count(title) as count from note_article group by author_id order by count desc limit 3")
    List<Long> getTop3AuthorId();
}
