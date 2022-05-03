package com.cauchynote.share.service.impl;

import com.cauchynote.share.entity.SharedArticle;
import com.cauchynote.share.mapper.ShareMapper;
import com.cauchynote.share.service.ShareService;
import com.cauchynote.utils.DateUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 分享服务层实现
 *
 * @author Cauchy
 * @ClassName ShareServiceImpl.java
 * @createTime 2022年04月13日 10:03:00
 */
@Service
@AllArgsConstructor
public class ShareServiceImpl implements ShareService {
    private ShareMapper shareMapper;

    @Override
    public int addArticleShare(Integer shareUserId, Integer receiveUserId, Integer articleId, Date shareDate, int validDay,
                               int isRevisable) {
        return shareMapper.addArticleShare(shareUserId, receiveUserId, articleId, shareDate, validDay, isRevisable);
    }

    @Override
    public List<SharedArticle> getSharedArticleList(Integer userId, Integer pageSize, Integer pageNum, String keyword) {
        List<SharedArticle> sharedArticleList = shareMapper.getSharedArticleList(userId, pageSize, (pageNum - 1) * pageSize,
            keyword);
        // 去掉已经过期的分享
        List<SharedArticle> resultList = new ArrayList<>();
        for (SharedArticle sharedArticle : sharedArticleList) {
            Date shareDate = sharedArticle.getShareDate();
            int validDay = sharedArticle.getValidDay();
            if (!DateUtil.getNumDayDate(-1 * validDay).after(shareDate)) {
                resultList.add(sharedArticle);
            }
        }
        return resultList;
    }

    @Override
    public int deleteArticleShare(Integer id) {
        return shareMapper.deleteArticleShare(id);
    }
}
