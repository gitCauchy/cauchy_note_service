package com.cauchynote.article.service.impl;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import com.cauchynote.system.mapper.UserMapper;
import com.cauchynote.utils.DateUtil;
import lombok.AllArgsConstructor;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Service;

import com.cauchynote.article.entity.Article;
import com.cauchynote.article.mapper.ArticleMapper;
import com.cauchynote.article.service.ArticleService;

import javax.servlet.http.HttpServletResponse;

/**
 * 文章服务实现
 *
 * @author Cauchy
 * @ClassName ArticleServiceImpl.java
 */
@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private ArticleMapper articleMapper;
    private UserMapper userMapper;
    private static final Integer STATISTIC_PERIOD = 6;

    @Override
    public Integer addArticle(Article article) {
        return articleMapper.addArticle(article);
    }

    @Override
    public Integer deleteArticle(Integer id) {
        return articleMapper.deleteArticle(id);
    }

    @Override
    public Integer modifyArticle(Article article) {
        return articleMapper.modifyArticle(article);
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
        Date startMonth = DateUtil.startBeforeNumMonth(today, -1);
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
        Date startMonth = DateUtil.startBeforeNumMonth(today, -1);
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
        // 获取本月 1 日的日期
        Date firstDayOfThisMonth = DateUtil.startBeforeNumMonth(new Date(), 0);
        Date[] dates = new Date[STATISTIC_PERIOD];
        int[] nCounts = new int[STATISTIC_PERIOD];
        int[] counts = new int[STATISTIC_PERIOD];

        for (int i = 0; i < STATISTIC_PERIOD; i++) {
            dates[i] = DateUtil.startBeforeNumMonth(firstDayOfThisMonth, i + 1);
            nCounts[i] = articleMapper.getUserArticleCount(authorId, dates[i], firstDayOfThisMonth);
        }
        for (int j = STATISTIC_PERIOD - 1; j > 0; j--) {
            counts[j] = nCounts[j] - nCounts[j - 1];
        }
        counts[0] = nCounts[0];
        return counts;
    }

    @Override
    public Map<String, Object> getTopThreeUserLastSixMonthArticleCount() {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Map<String, Object> resultMap = new HashMap<>(2);
        // 获取前三名用户 id
        List<Integer> top3AuthorId = articleMapper.getTop3AuthorId();
        List<Map<String, Integer>> dataList = new ArrayList<>();
        if (top3AuthorId.size() < 3) {
            resultMap.put("data", null);
        } else {
            // 根据 authorId 获取用户名
            String top1Username = userMapper.getUsernameById(top3AuthorId.get(0));
            String top2Username = userMapper.getUsernameById(top3AuthorId.get(1));
            String top3Username = userMapper.getUsernameById(top3AuthorId.get(2));
            int[][] topCount = new int[3][6];
            topCount[0] = getUserLastSixMonthArticleCount(top3AuthorId.get(0));
            topCount[1] = getUserLastSixMonthArticleCount(top3AuthorId.get(1));
            topCount[2] = getUserLastSixMonthArticleCount(top3AuthorId.get(2));

            for (int i = 0; i < STATISTIC_PERIOD; i++) {
                Map<String, Integer> tmp = new TreeMap<>();
                tmp.put(top1Username, topCount[0][i]);
                tmp.put(top2Username, topCount[1][i]);
                tmp.put(top3Username, topCount[2][i]);
                dataList.add(tmp);
            }
            Collections.reverse(dataList);
            resultMap.put("data", dataList);
        }
        List<String> dates = new ArrayList<>();
        for (int j = 0; j < STATISTIC_PERIOD; j++) {
            Date tmpDate = DateUtil.startBeforeNumMonth(today, j + 1);
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
    public List<Map<String, Object>> getTopUserWeekMonthYearTotalData() {
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
            monthData[i] = getUserArticleCount(top3AuthorId.get(i), DateUtil.startBeforeNumMonth(today, -1), today);
            yearData[i] = getUserArticleCount(top3AuthorId.get(i), DateUtil.startYear(today), today);
        }

        for (int i = 0; i < size; i++) {
            Map<String, Object> tmp = new HashMap<>(4);
            tmp.put("name", topUsername[i]);
            tmp.put("weekAdd", weekData[i]);
            tmp.put("monthAdd", monthData[i]);
            tmp.put("yearAdd", yearData[i]);
            result.add(tmp);
        }
        return result;
    }

    @Override
    public void exportContentToWord(Integer id, HttpServletResponse response) {
        Article article = articleMapper.getArticle(id);
        String content = article.getContent().replace("&lt;", "<")
            .replace("&gt;", ">")
            .replace("&quot;", "\"")
            .replace("&amp;", "&");
        // 处理完成的放在 <html><body> 标签中
        String handledContent = "<html><body>" + content + "</body></html>";
        // 设置编码
        byte[] b = handledContent.getBytes(StandardCharsets.UTF_8);
        //将字节数组包装到流中
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(b);

        byte[] buffer = new byte[1024];
        BufferedInputStream bufferedInputStream = null;
        OutputStream outputStream = null;
        try {
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            outputStream = response.getOutputStream();
            bufferedInputStream = new BufferedInputStream(byteArrayInputStream);
            while (bufferedInputStream.read(buffer) != -1) {
                outputStream.write(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
