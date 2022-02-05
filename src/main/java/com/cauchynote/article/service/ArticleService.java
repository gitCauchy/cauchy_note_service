package com.cauchynote.article.service;

import java.util.List;
import java.util.Map;

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
     * 添加文章
     * @param article 文章对象
     * @return 是否成功
     */
    boolean addArticle(Article article);

    /**
     * 删除文章
     * @param id 文章 ID
     * @return 是否成功
     */
    boolean deleteArticle(Integer id);

    /**
     * 修改日志
     * @param article
     * @return 是否成功
     */
    boolean modifyArticle(Article article);

    /**
     * 获取日志内容
     *
     * @param id 文章 ID
     * @return 文章对象
     */
    Article getArticle(Integer id);

    /**
     * 获取日志列表
     *
     * @param pageSize 页大小
     * @param pageNum 页号
     * @param keyword 关键词
     * @return 文章列表
     */
    List<Article> getArticleList(Long authorId, Integer pageSize, Integer pageNum, String keyword);

    /**
     * 获取总数
     * @param authorId 作者ID
     * @param keyword 关键词
     * @return 文章数量
     */
    Integer getArticleTotal(Long authorId, String keyword);

    /**
     * 获取用户文章数量
     *
     * @param authorId 作者ID
     * @return 文章数量
     */
    Map<String, Integer> getUserArticleCount(Long authorId);

    /**
     * 获取所有用户文章数量
     *
     * @return 文章数量
     */
    Map<String, Integer> getTotalArticleCount();
}
