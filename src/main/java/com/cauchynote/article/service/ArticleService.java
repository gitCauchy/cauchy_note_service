package com.cauchynote.article.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cauchynote.article.entity.Article;

/**
 * @author Cauchy
 * @ClassName ArticleService.java
 * @Date 2019年12月12日
 * @Description 日志服务层
 * @Version
 */
@Service
public interface ArticleService {
    /**
     * 新增日志
     *
     * @param article
     */
    void addArticle(Article article);

    /**
     * 删除日志
     *
     * @param id
     */
    void deleteArticle(Integer id);

    /**
     * 修改日志
     *
     * @param article
     */
    void modifyArticle(Article article);

    /**
     * 获取日志内容
     *
     * @param id
     * @return
     */
    Article getArticle(Integer id);

    /**
     * 获取日志列表
     *
     * @param pageSize
     * @param pageNum
     * @param keyword
     * @return
     */
    List<Article> getArticleList(Long authorId, Integer pageSize, Integer pageNum, String keyword);

    /**
     * @return
     * @description 获取总数
     */
    Integer getArticleTotal(Long authorId, String keyword);
}
