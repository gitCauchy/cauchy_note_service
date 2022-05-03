package com.cauchynote.share.controller;

import com.cauchynote.share.entity.SharedArticle;
import com.cauchynote.share.service.ShareService;
import com.cauchynote.utils.SystemConstantDefine;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 内容分享控制层
 *
 * @author Cauchy
 * @ClassName ShareController.java
 * @createTime 2022年04月13日 09:58:00
 */
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/share")
public class ShareController {
    private ShareService shareService;

    @PostMapping("/addArticleShare")
    public ResponseEntity<Integer> addArticleShare(@RequestBody Map<String, Integer> requestMap) {
        Integer shareUserId = requestMap.get("shareUserId");
        Integer receiveUserId = requestMap.get("receiveUserId");
        Integer articleId = requestMap.get("articleId");
        Date shareDate = new Date();
        Integer validDay = requestMap.get("validDay");
        Integer isRevisable = requestMap.get("isRevisable");
        int insertNum = shareService.addArticleShare(shareUserId, receiveUserId, articleId, shareDate, validDay,
            isRevisable);
        if (insertNum == 1) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    @GetMapping("/undoArticleShare")
    public ResponseEntity<Integer> undoArticleShare(@RequestParam(value = "id") Integer id) {
        int result = shareService.deleteArticleShare(id);
        if (result == 1) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    @GetMapping("/getSharedArticleList")
    public ResponseEntity<List<SharedArticle>> getSharedArticles(
        @RequestParam(value = "receiverId") Integer userId,
        @RequestParam(value = "pageSize") Integer pageSize,
        @RequestParam(value = "pageNum") Integer pageNum,
        @RequestParam(value = "keyword") String keyword) {
        return new ResponseEntity<>(shareService.getSharedArticleList(userId, pageSize, pageNum, keyword), HttpStatus.OK);
    }
}
