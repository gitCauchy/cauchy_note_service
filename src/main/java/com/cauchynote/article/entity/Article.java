package com.cauchynote.article.entity;

import java.util.Date;

import lombok.Data;


/**
 * 
 * @author Cauchy
 * @ClassName Article.java
 * @Date 2019年12月12日
 * @Description 文章实体类
 * @Version V0.1
 *
 */
@Data
public class Article {
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
	private String authorId;
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
