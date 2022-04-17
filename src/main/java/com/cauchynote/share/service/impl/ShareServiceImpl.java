package com.cauchynote.share.service.impl;

import com.cauchynote.share.entity.SharedArticle;
import com.cauchynote.share.mapper.ShareMapper;
import com.cauchynote.share.service.ShareService;
import com.cauchynote.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 分享服务层实现
 *
 * @author Cauchy
 * @ClassName ShareServiceImpl.java
 * @createTime 2022年04月13日 10:03:00
 */
@Service
public class ShareServiceImpl implements ShareService {
    @Autowired
    private ShareMapper shareMapper;

    @Override
    public int addArticleShare(Long shareUserId, Long receiveUserId, Long articleId, Date shareDate, int validDay,
                               int isRevisable) {
        return shareMapper.addArticleShare(shareUserId, receiveUserId, articleId, shareDate, validDay, isRevisable);
    }

    @Override
    public List<SharedArticle> getSharedArticleList(Long userId, Integer pageSize, Integer pageNum, String keyword) {
        List<SharedArticle> sharedArticleList = shareMapper.getSharedArticleList(userId, pageSize, (pageNum - 1) * pageSize,
            keyword);
        // 去掉已经过期的分享
        for (SharedArticle sharedArticle : sharedArticleList) {
            Date shareDate = sharedArticle.getShareDate();
            int validDay = sharedArticle.getValidDay();
            if (DateUtil.getNDayDate(-1 * validDay).compareTo(new Date()) > 0) {
                sharedArticleList.remove(sharedArticle);
            }
        }
        return sharedArticleList;
    }

    @Override
    public int deleteArticleShare(Long id) {
        return shareMapper.deleteArticleShare(id);
    }
}
