package com.cauchynote.article.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cauchynote.utils.DateUtil;
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
    public boolean addArticle(Article article) {
        return articleMapper.addArticle(article) == 1;
    }

    @Override
    public boolean deleteArticle(Integer id) {
        return articleMapper.deleteArticle(id) == 1;
    }

    @Override
    public boolean modifyArticle(Article article) {
        return articleMapper.modifyArticle(article) == 1;
    }

    @Override
    public Article getArticle(Integer id) {
        return articleMapper.getArticle(id);
    }

    @Override
    public List<Article> getArticleList(Long aurhorId, Integer pageSize, Integer pageNum, String keyword) {
        return articleMapper.getArticleList(aurhorId, pageSize, (pageNum - 1) * pageSize, keyword);
    }

    @Override
    public Integer getArticleTotal(Long authorId, String keyword) {
        return articleMapper.getArticleTotal(authorId, keyword);
    }

    @Override
    public Map<String, Integer> getUserArticleCount(Long authorId) {
        Date today = new Date();
        Date startWeek = DateUtil.startWeek(today);
        Date startMonth = DateUtil.startMonth(today);
        Date startYear = DateUtil.startYear(today);
        int countOfWeek = articleMapper.getUserArticleCount(authorId, startWeek);
        int countOfMonth = articleMapper.getUserArticleCount(authorId, startMonth);
        int countOfYear = articleMapper.getUserArticleCount(authorId, startYear);
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("countOfWeek", countOfWeek);
        resultMap.put("countOfMonth", countOfMonth);
        resultMap.put("countOfYear", countOfYear);
        return resultMap;
    }

    @Override
    public Map<String, Integer> getTotalArticleCount() {
        Date today = new Date();
        Date startWeek = DateUtil.startWeek(today);
        Date startMonth = DateUtil.startMonth(today);
        Date startYear = DateUtil.startYear(today);
        int countOfWeek = articleMapper.getTotalArticleCount(startWeek);
        int countOfMonth = articleMapper.getTotalArticleCount(startMonth);
        int countOfYear = articleMapper.getTotalArticleCount(startYear);
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("countOfWeek", countOfWeek);
        resultMap.put("countOfMonth", countOfMonth);
        resultMap.put("countOfYear", countOfYear);
        return resultMap;
    }

}
