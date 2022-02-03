package com.cauchynote.article.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @param article 文章对象
     * @return Http状态
     * @Description 新增文章
     */
    @PostMapping("/addArticle")
    public ResponseEntity<HttpStatus> addArticle(@RequestBody Article article) {
        articleService.addArticle(article);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * @param id 文章ID
     * @return Http状态
     * @Description 删除文章
     */
    @GetMapping("/deleteArticle")
    public ResponseEntity<HttpStatus> deleteArticle(@RequestParam Integer id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * @param article 文章对象
     * @return Http状态
     * @Description 修改文章
     */
    @PostMapping("/modifyArticle")
    public ResponseEntity<HttpStatus> modifyArticle(Article article) {
        articleService.modifyArticle(article);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * @param id 文章ID
     * @return 文章对象
     * @Description 获取文章
     */
    @GetMapping("/getArticle")
    public ResponseEntity<Article> getArticle(@RequestParam Integer id) {
        Article article = articleService.getArticle(id);
        return new ResponseEntity<Article>(article, HttpStatus.OK);
    }

    @GetMapping("/getArticleList")
    public ResponseEntity<Map> getArticleList(
            @RequestParam(value = "pageSize") Integer pageSize,
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "keyword") String keyword) {
        Map<String, Object> retMap = new HashMap<>();
        List<Article> articles = articleService.getArticleList(pageSize, pageNum, keyword);
        Integer total = articleService.getArticleTotal(keyword);
        retMap.put("articles", articles);
        retMap.put("total", total);
        if (articles != null) {
            return new ResponseEntity<>(retMap, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
