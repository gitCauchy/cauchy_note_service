package com.cauchynote.share.entity;

import com.cauchynote.article.entity.Article;
import lombok.Data;

import java.util.Date;

/**
 * 被分享的文章
 *
 * @author Cauchy
 * @ClassName SharedArticle.java
 * @createTime 2022年04月13日 14:44:00
 */
@Data
public class SharedArticle extends Article {
    /**
     * 分享 ID
     */
    private Long shareId;
    /**
     * 分享者 ID
     */
    private Long shareUserId;
    /**
     * 分享者用户名
     */
    private String shareUsername;
    /**
     * 接收者 ID
     */
    private Long receiverId;
    /**
     * 分享日期
     */
    private Date shareDate;
    /**
     * 有效期
     */
    private Integer validDay;
    /**
     * 是否可被编辑
     */
    private Integer isRevisable;
}
