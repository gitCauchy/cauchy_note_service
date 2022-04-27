package com.cauchynote.share.entity;

import com.cauchynote.article.entity.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 被分享的文章
 *
 * @author Cauchy
 * @ClassName SharedArticle.java
 * @createTime 2022年04月13日 14:44:00
 */
@Data
public class SharedArticle {
    /**
     * 分享 ID
     */
    private Integer shareId;
    /**
     * 分享者 ID
     */
    private Integer shareUserId;
    /**
     * 分享者用户名
     */
    private String shareUsername;
    /**
     * 接收者 ID
     */
    private Integer receiverId;
    /**
     * 分享日期
     */
    private Date shareDate;
    /**
     * 有效期
     */
    private Integer validDay;
    /**
     * 是否可被编辑  0 - 是  1 - 否
     */
    private Integer isRevisable;
    /**
     * 文章ID
     */
    private Integer id;
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
    private Integer authorId;
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
