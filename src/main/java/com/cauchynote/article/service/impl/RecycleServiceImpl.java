package com.cauchynote.article.service.impl;

import com.cauchynote.article.entity.Article;
import com.cauchynote.article.mapper.RecycleMapper;
import com.cauchynote.article.service.RecycleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 回收站服务实现
 *
 * @Author lingling
 * @Date 2022/4/23
 */
@AllArgsConstructor
@Service
public class RecycleServiceImpl implements RecycleService {
    private RecycleMapper recycleMapper;

    @Override
    public List<Article> getDeleteArticleList(Integer authorId, Integer pageSize, Integer pageNum, String keyword) {
        return recycleMapper.getDeleteArticleList(authorId, pageSize, (pageNum - 1) * pageSize, keyword);
    }

    @Override
    public Integer getDeleteArticleTotal(Integer authorId, String keyword) {
        return recycleMapper.getDeleteArticleTotal(authorId, keyword);
    }

    @Override
    public Integer deleteArticleByIdPhysical(Integer id) {
        return recycleMapper.deleteArticleByIdPhysical(id);
    }

    @Override
    public Integer restoreArticleById(Integer id) {
        return recycleMapper.restoreArticleById(id);
    }

    @Override
    public Integer deleteAllArticlePhysical(Integer authorId) {
        return recycleMapper.deleteAllArticlePhysical(authorId);
    }

    @Override
    public Integer restoreAllArticle(Integer authorId) {
        return recycleMapper.restoreAllArticle(authorId);
    }
}
