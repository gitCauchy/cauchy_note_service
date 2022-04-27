package com.cauchynote.article.service.impl;

import com.cauchynote.article.entity.Article;
import com.cauchynote.article.mapper.RecycleMapper;
import com.cauchynote.article.service.RecycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 回收站服务实现
 *
 * @Author lingling
 * @Date 2022/4/23
 */
@Service
public class RecycleServiceImpl implements RecycleService {
    @Autowired
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
    public boolean deleteArticleByIdPhysical(Integer id) {
        return recycleMapper.deleteArticleByIdPhysical(id) == 1;
    }

    @Override
    public boolean restoreArticleById(Integer id) {
        return recycleMapper.restoreArticleById(id) == 1;
    }

    @Override
    public boolean deleteAllArticlePhysical(Integer authorId) {
        return recycleMapper.deleteAllArticlePhysical(authorId) > 0;
    }

    @Override
    public boolean restoreAllArticle(Integer authorId) {
        return recycleMapper.restoreAllArticle(authorId) > 0;
    }
}
