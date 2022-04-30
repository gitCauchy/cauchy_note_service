package com.cauchynote.article.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cauchynote.system.service.LoginInfoService;
import com.cauchynote.utils.SystemConstantDefine;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cauchynote.article.entity.Article;
import com.cauchynote.article.service.ArticleService;

/**
 * 文章控制层
 *
 * @author Cauchy
 * @ClassName ArticleController.java
 */
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    ArticleService articleService;
    LoginInfoService loginInfoService;
    /**
     * 新增文章
     *
     * @param article 文章对象
     * @return 状态码
     */
    @PostMapping("/addArticle")
    public ResponseEntity<Integer> addArticle(@RequestBody Article article) {
        article.setStatus(0);
        Integer result = articleService.addArticle(article);
        if (result == 1) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    /**
     * 删除文章
     *
     * @param id 文章ID
     * @return 状态码
     */
    @GetMapping("/deleteArticle")
    public ResponseEntity<Integer> deleteArticle(@RequestParam Integer id) {
        Integer result = articleService.deleteArticle(id);
        if (result == 1) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    /**
     * 修改文章
     *
     * @param article 文章对象
     * @return 状态码
     */
    @PostMapping("/modifyArticle")
    public ResponseEntity<Integer> modifyArticle(@RequestBody Article article) {
        Integer result = articleService.modifyArticle(article);
        if (result == 1) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    /**
     * 获取文章信息
     *
     * @param id 文章ID
     * @return 文章对象
     */
    @GetMapping("/getArticle")
    public ResponseEntity<Article> getArticle(@RequestParam Integer id) {
        Article article = articleService.getArticle(id);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    /**
     * 获取文章列表
     *
     * @param authorId 作者ID
     * @param pageSize 页大小
     * @param pageNum  页数
     * @param keyword  关键词
     * @return 文章列表
     */
    @GetMapping("/getArticleList")
    public ResponseEntity<Map<String, Object>> getArticleList(@RequestParam(value = "authorId") Integer authorId, @RequestParam(value = "pageSize") Integer pageSize, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "keyword") String keyword) {
        Map<String, Object> retMap = new HashMap<>(2);
        List<Article> articles = articleService.getArticleList(authorId, pageSize, pageNum, keyword);
        Integer total = articleService.getArticleTotal(authorId, keyword);
        retMap.put("articles", articles);
        retMap.put("total", total);
        if (articles != null) {
            return new ResponseEntity<>(retMap, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * 获取文章统计数据
     *
     * @param authorId 作者 ID
     * @return 文章统计数据
     */
    @GetMapping("/articleCountInfo")
    public ResponseEntity<Map<String, Map<String, Integer>>> getLoginCount(@RequestParam(value = "authorId") Integer authorId) {
        Map<String, Integer> userWeekMonthYearArticleInfo = articleService.getUserWeekMonthYearArticleCount(authorId);
        Map<String, Integer> totalWeekMonthYearArticleInfo = articleService.getTotalWeekMonthYearArticleCount();
        Map<String, Map<String, Integer>> returnMap = new HashMap<>(2);
        returnMap.put("user", userWeekMonthYearArticleInfo);
        returnMap.put("total", totalWeekMonthYearArticleInfo);
        return new ResponseEntity<>(returnMap, HttpStatus.OK);
    }

    /**
     * 获取top 用户 周 月 年 数据
     *
     * @return 用户 周 月 年 数据
     */
    @GetMapping("/getTableData")
    public ResponseEntity<List<Map<String, Object>>> getTableData() {
        List<Map<String, Object>> topUserWeekMonthTotalData = articleService.getTopUserWeekMonthTotalData();
        return new ResponseEntity<>(topUserWeekMonthTotalData, HttpStatus.OK);
    }

    /**
     * 获取数据变化趋势数据
     *
     * @return 数据变化趋势数据
     */
    @GetMapping("/getTrendData")
    public ResponseEntity<Map<String, Object>> getTopThreeUserLastSixMonthArticleCount() {
        Map<String, Object> topThreeUserLastSixMonthArticleCount = articleService.getTopThreeUserLastSixMonthArticleCount();
        return new ResponseEntity<>(topThreeUserLastSixMonthArticleCount, HttpStatus.OK);
    }

    /**
     * 获取登录及文章数量数据
     *
     * @param authorId 作者 ID
     * @return 登录及文章数量数据
     */
    @GetMapping("/getCountData")
    public ResponseEntity<Map<String, Map<String, Integer>>> getCountData(@RequestParam(value = "authorId") Integer authorId) {
        Map<String, Integer> userData = articleService.getUserWeekMonthYearArticleCount(authorId);
        Map<String, Integer> userLoginData = loginInfoService.getUserLoginCount(authorId);
        Map<String, Map<String, Integer>> result = new HashMap<>(2);
        result.put("userArticleData", userData);
        result.put("userLoginData", userLoginData);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
