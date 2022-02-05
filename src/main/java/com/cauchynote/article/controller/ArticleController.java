package com.cauchynote.article.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cauchynote.utils.SystemConstantDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cauchynote.article.entity.Article;
import com.cauchynote.article.service.ArticleService;

/**
 * @author Cauchy
 * @ClassName ArticleController.java
 * @Date 2019年12月12日
 * @Description 文章控制层
 * @Version V0.1
 */
@CrossOrigin
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    /**
     * 新增文章
     *
     * @param article 文章对象
     * @return ResponseEntity
     */
    @PostMapping("/addArticle")
    public ResponseEntity<Integer> addArticle(@RequestBody Article article) {
        boolean result = articleService.addArticle(article);
        if (result) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    /**
     * 删除文章
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteArticle")
    public ResponseEntity<Integer> deleteArticle(@RequestParam Integer id) {
        boolean result = articleService.deleteArticle(id);
        if (result) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    /**
     * 修改文章
     *
     * @param article 文章对象
     * @return
     */
    @PostMapping("/modifyArticle")
    public ResponseEntity<Integer> modifyArticle(@RequestBody Article article) {
        boolean result = articleService.modifyArticle(article);
        if (result) {
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
        return new ResponseEntity<Article>(article, HttpStatus.OK);
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
    public ResponseEntity<Map> getArticleList(
            @RequestParam(value = "authorId") Long authorId,
            @RequestParam(value = "pageSize") Integer pageSize,
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "keyword") String keyword) {
        Map<String, Object> retMap = new HashMap<>();
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

    @GetMapping("/articleCountInfo")
    public ResponseEntity<Map<String, Map>> getLoginCount(@RequestParam(value = "authorId") Long authorId) {
        Map userLoginInfo = articleService.getUserArticleCount(authorId);
        Map totalLoginInfo = articleService.getTotalArticleCount();
        Map<String, Map> returnMap = new HashMap<>(2);
        returnMap.put("user", userLoginInfo);
        returnMap.put("total", totalLoginInfo);
        return new ResponseEntity<>(returnMap, HttpStatus.OK);
    }
}
