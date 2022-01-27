package com.cauchynote.article.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cauchynote.article.entity.Article;
import com.cauchynote.article.service.ArticleService;

/**
 *
 * @author Cauchy
 * @ClassName ArticleController.java
 * @Date 2019年12月12日
 * @Description 文章控制层
 * @Version V0.1
 *
 */
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
	public ResponseEntity<List<Article>> getArticleList(){
		List<Article> articleList = articleService.getArticleList();
		return new ResponseEntity<>(articleList, HttpStatus.OK);
	}
}
