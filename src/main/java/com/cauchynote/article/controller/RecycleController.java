package com.cauchynote.article.controller;

import com.cauchynote.article.entity.Article;
import com.cauchynote.article.service.RecycleService;
import com.cauchynote.utils.SystemConstantDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 回收站
 *
 * @Author lingling
 * @Date 2022/4/23
 */
@CrossOrigin
@RestController
@RequestMapping("/recycle")
public class RecycleController {
    @Autowired
    private RecycleService recycleService;

    /**
     * 获取所有逻辑删除的文章
     *
     * @param authorId 作者 ID
     * @param pageSize 页大小
     * @param pageNum  页号
     * @param keyword  搜索关键词
     * @return 回收站列表
     */
    @GetMapping("/getRecycleArticleList")
    public ResponseEntity<Map<String, Object>> geRecycleArticleList(@RequestParam(value = "authorId") Long authorId,
                                                                    @RequestParam(value = "pageSize") Integer pageSize,
                                                                    @RequestParam(value = "pageNum") Integer pageNum,
                                                                    @RequestParam(value = "keyword") String keyword) {
        Map<String, Object> retMap = new HashMap<>(2);
        List<Article> deleteArticleList = recycleService.getDeleteArticleList(authorId, pageSize, pageNum, keyword);
        Integer deleteArticleTotal = recycleService.getDeleteArticleTotal(authorId, keyword);
        retMap.put("recycleAtricle", deleteArticleList);
        retMap.put("recycleTotal", deleteArticleTotal);
        if (deleteArticleList != null) {
            return new ResponseEntity<>(retMap, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * 清空回收站里所有的文章
     *
     * @param authorId 作者ID
     * @return 装态码
     */
    @GetMapping("/deleteAllArticlePhysical")
    public ResponseEntity<Integer> deleteAllArticlePhysical(@RequestParam Long authorId) {
        boolean result = recycleService.deleteAllArticlePhysical(authorId);
        if (result) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    /**
     * 根据文章id彻底删除
     *
     * @param id 文章ID
     * @return 装态码
     */
    @GetMapping("/deleteArticlePhysical")
    public ResponseEntity<Integer> deleteArticlePhysical(@RequestParam Long id) {
        boolean result = recycleService.deleteArticleByIdPhysical(id);
        if (result) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    /**
     * 回复所有的文章
     *
     * @param authorId 作者ID
     * @return 装态码
     */
    @PostMapping("/restoreAllArticle")
    public ResponseEntity<Integer> restoreAllArticle(@RequestParam Long authorId) {
        boolean result = recycleService.restoreAllArticle(authorId);
        System.out.println(result);
        if (result) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    /**
     * 恢复回收站
     *
     * @param id 文章ID
     * @return 状态码
     */
    @PostMapping("/restoreArticle")
    public ResponseEntity<Integer> restoreArticle(@RequestParam Long id) {
        boolean result = recycleService.restoreArticleById(id);
        if (result) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }
}
