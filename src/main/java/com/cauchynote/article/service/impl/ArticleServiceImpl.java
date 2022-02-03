package com.cauchynote.article.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cauchynote.article.entity.Article;
import com.cauchynote.article.mapper.ArticleMapper;
import com.cauchynote.article.service.ArticleService;

/**
 * @author Cauchy
 * @ClassName ArticleServiceImpl.java
 * @Date 2019年12月12日
 * @Description 文章服务实现
 * @Version V0.1
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    @Override
    public void addArticle(Article article) {
        articleMapper.addArticle(article);
    }

    @Override
    public void deleteArticle(Integer id) {
        articleMapper.deleteArticle(id);
    }

    @Override
    public void modifyArticle(Article article) {
        articleMapper.modifyArticle(article);
    }

    @Override
    public Article getArticle(Integer id) {
        Article article = articleMapper.getArticle(id);
        return article;
    }

    @Override
    public List<Article> getArticleList(Long aurhorId, Integer pageSize, Integer pageNum, String keyword) {
        List<Article> articleList = articleMapper.getArticleList(aurhorId, pageSize, (pageNum - 1) * pageSize, keyword);
        return articleList;
    }

    @Override
    public Integer getArticleTotal(Long authorId, String keyword) {
        return articleMapper.getArticleTotal(authorId, keyword);
    }

}
