package com.cauchynote.article.service;

import com.cauchynote.article.entity.Article;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author lingling
 * @Description
 * @Date 2022/4/23
 * @Version
 */
@Service
public interface RecycleService {
    /**
     * 获取所有已经删除的文章
     * @param authorId
     * @param pageSize
     * @param pageNum
     * @param keyword
     * @return
     */
    List<Article> getDeleteArticleList(Long authorId, Integer pageSize, Integer pageNum, String keyword);

    /**
     * 获取已经删除的文章总数
     * @param authorId
     * @param keyword
     * @return
     */
    Integer getDeleteArticleTotal(Long authorId, String keyword);
    /**
     * 根据文章id彻底从数据库中删除文章
     * @param id
     * @return
     */
    boolean deleteArticleByIdPhysical(Long id);

    /**
     * 根据文章id恢复文章
     * @param id
     * @return
     */
    boolean restoreArticleById(Long id);

    /**
     * 清空回收站离所有的文章
     * @param authorId
     * @return
     */
    boolean deleteAllArticlePhysical(Long authorId);

    /**
     * 恢复回收站离所有的文章
     * @param authorId
     * @return
     */
    boolean restoreAllArticle(Long authorId);

}
