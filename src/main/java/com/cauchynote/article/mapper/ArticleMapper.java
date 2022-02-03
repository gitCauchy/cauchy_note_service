package com.cauchynote.article.mapper;

import java.util.Date;
import java.util.List;

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
    void addArticle(Article article);

    @Update("update note_article set status = 1 where id = #{id}")
    void deleteArticle(Integer id);

    @Update("update note_article set title = #{title}, modify_time = now(), content = #{content} where id = #{id}")
    void modifyArticle(Article article);

    @Select("select id, title, content, author_id, create_time, status from note_article where id = #{id}")
    @Results(id = "getArticleDetailInformation", value = {
            @Result(column = "id", property = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(column = "title", property = "title", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "content", property = "content", javaType = String.class, jdbcType = JdbcType.LONGNVARCHAR),
            @Result(column = "author_id", property = "authorId", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", javaType = Integer.class, jdbcType = JdbcType.INTEGER)})
    Article getArticle(Integer id);

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

    @Select("select count(id) from note_article where author_id = #{authorId} and title like '%${keyword}%'")
    Integer getArticleTotal(Long authorId, String keyword);

}
