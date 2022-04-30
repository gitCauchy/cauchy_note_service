package com.cauchynote.share.service;

import com.cauchynote.share.entity.SharedArticle;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 分享服务层接口
 *
 * @author Cauchy
 * @ClassName ShareService.java
 * @createTime 2022年04月13日 10:02:00
 */

@Service
public interface ShareService {
    /**
     * 添加文章分享
     *
     * @param shareUserId   分享者 ID
     * @param receiveUserId 接受者 ID
     * @param articleId     文章 ID
     * @param shareDate     分享日期
     * @param validDay      分享有效期
     * @param isRevisable   是否可修改
     * @return 插入数据条数
     */
    int addArticleShare(Long shareUserId, Long receiveUserId, Long articleId, Date shareDate, int validDay,
                        int isRevisable);

    /**
     * 获取用户被分享的日志
     *
     * @param userId   用户 ID
     * @param pageSize 页大小
     * @param pageNum  页号
     * @param keyword  搜索关键词
     * @return List<Article>
     */
    List<SharedArticle> getSharedArticleList(Long userId, Integer pageSize, Integer pageNum, String keyword);

    /**
     * 删除分享信息
     *
     * @param id 分享信息 ID
     * @return 1 - 删除成功 0 - 删除失败
     */
    int deleteArticleShare(Long id);
}
