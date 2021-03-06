package com.cauchynote.article.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.cauchynote.article.entity.Article;

import javax.servlet.http.HttpServletResponse;

/**
 * 文章服务层
 *
 * @author Cauchy
 * @ClassName ArticleService.java
 */
@Service
public interface ArticleService {
    /**
     * 添加文章
     *
     * @param article 文章对象
     * @return 1 - 添加成功 | 添加失败
     */
    Integer addArticle(Article article);

    /**
     * 删除文章
     *
     * @param id 文章 ID
     * @return 1 - 删除成功 | 0 - 删除失败
     */
    Integer deleteArticle(Integer id);

    /**
     * 修改日志
     *
     * @param article 文章对象
     * @return 1 - 修改成功 0 - 修改失败
     */
    Integer modifyArticle(Article article);

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
     * @param authorId 作者 ID
     * @param pageSize 页大小
     * @param pageNum  页号
     * @param keyword  关键词
     * @return 文章列表
     */
    List<Article> getArticleList(Integer authorId, Integer pageSize, Integer pageNum, String keyword);

    /**
     * 获取总数
     *
     * @param authorId 作者ID
     * @param keyword  关键词
     * @return 文章数量
     */
    Integer getArticleTotal(Integer authorId, String keyword);

    /**
     * 获取用户文章数量
     *
     * @param authorId 作者ID
     * @return 文章数量
     */
    Map<String, Integer> getUserWeekMonthYearArticleCount(Integer authorId);

    /**
     * 获取所有用户文章数量
     *
     * @return 文章数量
     */
    Map<String, Integer> getTotalWeekMonthYearArticleCount();

    /**
     * 获取前六个月新增文章数量
     *
     * @param authorId 作者 ID
     * @return 新增数量
     */
    int[] getUserLastSixMonthArticleCount(Integer authorId);

    /**
     * 获取 Top 3 前六个月的数据
     *
     * @return top 3 用户前6个月数据
     */
    Map<String, Object> getTopThreeUserLastSixMonthArticleCount();

    /**
     * 获取用户文章数
     *
     * @param authorId  作者ID
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 用户文章数
     */
    Integer getUserArticleCount(Integer authorId, Date startDate, Date endDate);

    /**
     * 获取 top 用户 周月年 数据
     *
     * @return top 用户 周月年 数据
     */
    List<Map<String, Object>> getTopUserWeekMonthYearTotalData();

    /**
     * 笔记导出 word
     *
     * @param id       文章 ID
     * @param response HttpServletResponse
     */
    void exportContentToWord(Integer id, HttpServletResponse response);
}
