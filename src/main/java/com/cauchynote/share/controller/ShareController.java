package com.cauchynote.share.controller;

import com.cauchynote.share.entity.SharedArticle;
import com.cauchynote.share.service.ShareService;
import com.cauchynote.utils.SystemConstantDefine;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/share")
public class ShareController {
    @Autowired
    private ShareService shareService;

    @PostMapping("/addArticleShare")
    public ResponseEntity<Integer> addArticleShare(@RequestBody Map<String, Object> requestMap) {
        Long shareUserId = Long.parseLong((String) requestMap.get("shareUserId"));
        Long receiveUserId = Long.parseLong((String) requestMap.get("receiveUserId"));
        Long articleId = Long.parseLong((String) requestMap.get("articleId"));
        Date shareDate = (Date) requestMap.get("shareDate");
        Integer validDay = (Integer) requestMap.get("validDay");
        Integer isRevisable = (Integer) requestMap.get("isRevisable");
        int insertNum = shareService.addArticleShare(shareUserId, receiveUserId, articleId, shareDate, validDay,
            isRevisable);
        if (insertNum == 1) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    @GetMapping("/undoArticleShare")
    public ResponseEntity<Integer> undoArticleShare(@RequestParam(value = "id") Long id) {
        int result = shareService.deleteArticleShare(id);
        if (result == 1) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    @GetMapping("/getSharedArticleList")
    public ResponseEntity<List<SharedArticle>> getSharedArticles(
        @RequestParam(value = "receiverId") Long userId,
        @RequestParam(value = "pageSize") Integer pageSize,
        @RequestParam(value = "pageNum") Integer pageNum,
        @RequestParam(value = "keyword") String keyword) {
        return new ResponseEntity<>(shareService.getSharedArticleList(userId, pageSize, pageNum, keyword), HttpStatus.OK);
    }
}