package com.cauchynote.article.service;

import com.cauchynote.article.entity.Article;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 回收站服务层
 *
 * @Author lingling
 * @Date 2022/4/23
 */
@Service
public interface RecycleService {
    /**
     * 获取所有已经删除的文章
     *
     * @param authorId 作者ID
     * @param pageSize 页大小
     * @param pageNum  页号
     * @param keyword  搜索关键词
     * @return List
     */
    List<Article> getDeleteArticleList(Integer authorId, Integer pageSize, Integer pageNum, String keyword);

    /**
     * 获取已经删除的文章总数
     *
     * @param authorId 作者ID
     * @param keyword  搜索关键词
     * @return total
     */
    Integer getDeleteArticleTotal(Integer authorId, String keyword);

    /**
     * 根据文章id彻底从数据库中删除文章
     *
     * @param id 文章ID
     * @return true|false
     */
    boolean deleteArticleByIdPhysical(Integer id);

    /**
     * 根据文章id恢复文章
     *
     * @param id 文章ID
     * @return true|false
     */
    boolean restoreArticleById(Integer id);

    /**
     * 清空回收站离所有的文章
     *
     * @param authorId 作者ID
     * @return true|false
     */
    boolean deleteAllArticlePhysical(Integer authorId);

    /**
     * 恢复回收站离所有的文章
     *
     * @param authorId 作者ID
     * @return true|false
     */
    boolean restoreAllArticle(Integer authorId);
}
