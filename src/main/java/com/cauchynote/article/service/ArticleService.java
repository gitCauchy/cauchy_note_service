package com.cauchynote.article.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cauchynote.article.entity.Article;

/**
 * 
 * @author Cauchy
 * @ClassName ArticleService.java
 * @Date 2019年12月12日
 * @Description TODO
 * @Version 
 *
 */
@Service
public interface ArticleService {
	void addArticle(Article article);
	void deleteArticle(Integer id);
	void modifyArticle(Article article);
	Article getArticle(Integer id);
	List<Article> getArticleList();
}
