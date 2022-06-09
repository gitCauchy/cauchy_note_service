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
    public List<Map<String,Object>> getSharedArticleList(Integer userId, Integer pageSize, Integer pageNum,
                                                     String keyword) {
        List<Map<String, Object>> sharedArticleList = shareMapper.getSharedArticleList(userId, pageSize,
            (pageNum - 1) * pageSize,
            keyword);
        // 去掉已经过期的分享
        List<Map<String,Object>> resultList = new ArrayList<>();
        for (Map<String, Object> sharedArticle : sharedArticleList) {
            Map<String,Object> result = new HashMap<>();
            result.put("shareId",sharedArticle.get("id"));
            result.put("shareUserId",sharedArticle.get("share_user_id"));
            result.put("receiverId",sharedArticle.get("receiver_user_id"));
            result.put("id",sharedArticle.get("articleId"));
            result.put("shareDate",sharedArticle.get("share_date"));
            result.put("validDay",sharedArticle.get("valid_day"));
            result.put("isRevisable",sharedArticle.get("is_revisable"));
            result.put("title",sharedArticle.get("title"));
            result.put("content",sharedArticle.get("content"));
            result.put("createTime",sharedArticle.get("create_time"));
            result.put("modifyTime",sharedArticle.get("modifyTime"));
            result.put("status",sharedArticle.get("status"));
            result.put("shareUsername",sharedArticle.get("user_name"));
            result.put("remarkName",sharedArticle.get("remark_name"));
            Date shareDate = (Date) sharedArticle.get("share_date");
            int validDay = (int) sharedArticle.get("valid_day");
            if (!DateUtil.getNumDayDate(-1 * validDay).after(shareDate)) {
                resultList.add(result);
            }
        }
        return resultList;
    }

    @Override
    public Integer deleteArticleShare(Integer id) {
        return shareMapper.deleteArticleShare(id);
    }

    @Override
    public Integer getArticleShareCount(Integer shareUserId, Integer receiveUserId, Integer articleId) {
        List<SharedArticle> sharedArticleList = shareMapper.getArticleShareCount(shareUserId, receiveUserId, articleId);
        Integer count = 0;
        // 遍历列表，是否存在未过期的分享
        for (SharedArticle sharedArticle : sharedArticleList) {
            if (DateUtil.getNumDayDate(sharedArticle.getValidDay()).after(sharedArticle.getShareDate())) {
                count++;
            }
        }
        return count;
    }
}
