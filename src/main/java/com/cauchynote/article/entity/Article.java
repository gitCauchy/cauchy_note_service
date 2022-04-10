package com.cauchynote.article.entity;

import java.util.Date;

import lombok.Data;


/**
 * 文章实体类
 *
 * @author Cauchy
 * @ClassName Article.java
 */
@Data
public class Article {
    /**
     * 文章ID
     */
    private Long id;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 文章作者ID
     */
    private Long authorId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 状态
     */
    private Integer status;
}
