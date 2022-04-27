package com.cauchynote.article.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import com.cauchynote.system.mapper.UserMapper;
import com.cauchynote.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cauchynote.article.entity.Article;
import com.cauchynote.article.mapper.ArticleMapper;
import com.cauchynote.article.service.ArticleService;

/**
 * 文章服务实现
 *
 * @author Cauchy
 * @ClassName ArticleServiceImpl.java
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean addArticle(Article article) {
        return articleMapper.addArticle(article) == 1;
    }

    @Override
    public boolean deleteArticle(Integer id) {
        return articleMapper.deleteArticle(id) == 1;
    }

    @Override
    public boolean modifyArticle(Article article) {
        return articleMapper.modifyArticle(article) == 1;
    }

    @Override
    public Article getArticle(Integer id) {
        return articleMapper.getArticle(id);
    }

    @Override
    public List<Article> getArticleList(Integer authorId, Integer pageSize, Integer pageNum, String keyword) {
        return articleMapper.getArticleList(authorId, pageSize, (pageNum - 1) * pageSize, keyword);
    }

    @Override
    public Integer getArticleTotal(Integer authorId, String keyword) {
        return articleMapper.getArticleTotal(authorId, keyword);
    }

    @Override
    public Map<String, Integer> getUserWeekMonthYearArticleCount(Integer authorId) {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DATE, 1);
        Date tomorrow = calendar.getTime();
        Date startWeek = DateUtil.startWeek(today);
        Date startMonth = DateUtil.startBeforeNMonth(today, -1);
        Date startYear = DateUtil.startYear(today);
        int countOfWeek = articleMapper.getUserArticleCount(authorId, startWeek, tomorrow);
        int countOfMonth = articleMapper.getUserArticleCount(authorId, startMonth, tomorrow);
        int countOfYear = articleMapper.getUserArticleCount(authorId, startYear, tomorrow);
        Map<String, Integer> resultMap = new HashMap<>(3);
        resultMap.put("countOfWeek", countOfWeek);
        resultMap.put("countOfMonth", countOfMonth);
        resultMap.put("countOfYear", countOfYear);
        return resultMap;
    }

    @Override
    public Map<String, Integer> getTotalWeekMonthYearArticleCount() {
        Date today = new Date();
        Date startWeek = DateUtil.startWeek(today);
        Date startMonth = DateUtil.startBeforeNMonth(today, -1);
        Date startYear = DateUtil.startYear(today);
        int countOfWeek = articleMapper.getTotalArticleCount(startWeek);
        int countOfMonth = articleMapper.getTotalArticleCount(startMonth);
        int countOfYear = articleMapper.getTotalArticleCount(startYear);
        Map<String, Integer> resultMap = new HashMap<>(3);
        resultMap.put("countOfWeek", countOfWeek);
        resultMap.put("countOfMonth", countOfMonth);
        resultMap.put("countOfYear", countOfYear);
        return resultMap;
    }

    @Override
    public int[] getUserLastSixMonthArticleCount(Integer authorId) {
        // 获取前六个月月初的日期
        Date today = new Date();
        Date[] dates = new Date[6];
        int[] nCounts = new int[6];
        int[] counts = new int[6];

        for (int i = 0; i < 6; i++) {
            dates[i] = DateUtil.startBeforeNMonth(today, i + 1);
            nCounts[i] = articleMapper.getUserArticleCount(authorId, dates[i], today);
        }
        for (int j = 5; j > 0; j--) {
            counts[j] = nCounts[j] - nCounts[j - 1];
        }
        int countOfThisMonth = articleMapper.getUserArticleCount(authorId, DateUtil.startBeforeNMonth(today, 0), today);
        counts[0] = nCounts[0] - countOfThisMonth;
        return counts;
    }

    @Override
    public Map<String, Object> getTopThreeUserLastSixMonthArticleCount() {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Map<String, Object> resultMap = new HashMap<>();
        // 获取前三名用户 id
        List<Integer> top3AuthorId = articleMapper.getTop3AuthorId();
        // 根据 authorId 获取用户名
        String top1Username = userMapper.getUsernameById(top3AuthorId.get(0));
        String top2Username = userMapper.getUsernameById(top3AuthorId.get(1));
        String top3Username = userMapper.getUsernameById(top3AuthorId.get(2));
        int[][] topCount = new int[3][6];
        topCount[0] = getUserLastSixMonthArticleCount(top3AuthorId.get(0));
        topCount[1] = getUserLastSixMonthArticleCount(top3AuthorId.get(1));
        topCount[2] = getUserLastSixMonthArticleCount(top3AuthorId.get(2));
        for (int i = 0; i < 6; i++) {
            topCount[0][i] = articleMapper.getUserArticleCount(top3AuthorId.get(0),
                DateUtil.startBeforeNMonth(today, i), DateUtil.startBeforeNMonth(today, i - 1));
            topCount[1][i] = articleMapper.getUserArticleCount(top3AuthorId.get(1),
                DateUtil.startBeforeNMonth(today, i), DateUtil.startBeforeNMonth(today, i - 1));
            topCount[2][i] = articleMapper.getUserArticleCount(top3AuthorId.get(2),
                DateUtil.startBeforeNMonth(today, i), DateUtil.startBeforeNMonth(today, i - 1));
        }
        List<Map<String, Integer>> dataList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Map<String, Integer> tmp = new TreeMap<>();
            tmp.put(top1Username, topCount[0][i]);
            tmp.put(top2Username, topCount[1][i]);
            tmp.put(top3Username, topCount[2][i]);
            dataList.add(tmp);
        }
        Collections.reverse(dataList);
        resultMap.put("data", dataList);
        List<String> dates = new ArrayList<>();
        for (int j = 0; j < 6; j++) {
            Date tmpDate = DateUtil.startBeforeNMonth(today, j);
            dates.add(sdf.format(tmpDate));
        }
        Collections.reverse(dates);
        resultMap.put("dates", dates);
        return resultMap;
    }

    @Override
    public Integer getUserArticleCount(Integer authorId, Date startDate, Date endDate) {
        return articleMapper.getUserArticleCount(authorId, startDate, endDate);
    }

    @Override
    public List<Map<String, Object>> getTopUserWeekMonthTotalData() {
        List<Map<String, Object>> result = new ArrayList<>();
        Date today = new Date();
        // 获取前三名用户 id
        List<Integer> top3AuthorId = articleMapper.getTop3AuthorId();
        // 根据 authorId 获取用户名
        int size = Math.min(top3AuthorId.size(), 3);
        String[] topUsername = new String[size];
        for (int i = 0; i < size; i++) {
            topUsername[i] = userMapper.getUsernameById(top3AuthorId.get(i));
        }
        int[] weekData = new int[size];
        int[] monthData = new int[size];
        int[] yearData = new int[size];

        for (int i = 0; i < size; i++) {
            weekData[i] = getUserArticleCount(top3AuthorId.get(i), DateUtil.startWeek(today), today);
            monthData[i] = getUserArticleCount(top3AuthorId.get(i), DateUtil.startBeforeNMonth(today, -1), today);
            yearData[i] = getUserArticleCount(top3AuthorId.get(i), DateUtil.startYear(today), today);
        }

        for (int i = 0; i < size; i++) {
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("name", topUsername[i]);
            tmp.put("weekAdd", weekData[i]);
            tmp.put("monthAdd", monthData[i]);
            tmp.put("yearAdd", yearData[i]);
            result.add(tmp);
        }
        return result;
    }
}
