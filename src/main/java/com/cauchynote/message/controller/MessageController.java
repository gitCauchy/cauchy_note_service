package com.cauchynote.message.controller;

import com.cauchynote.message.entity.Message;
import com.cauchynote.message.service.MessageService;
import com.cauchynote.utils.SystemConstantDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 消息控制层
 *
 * @author Cauchy
 * @ClassName MessageController.java
 * @createTime 2022年04月25日 11:16:00
 */
@RestController
@RequestMapping("/message")
@CrossOrigin
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/addNewMessage")
    public ResponseEntity<Integer> addNewMessage(@RequestBody Message message) {
        Integer result = messageService.addNewMessage(message);
        if (result == 1) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    @GetMapping("/getMessageList")
    public ResponseEntity<List<Message>> getMessageList(@RequestParam Long receiverId) {
        List<Message> list = messageService.getMessageList(receiverId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/readMessage")
    public ResponseEntity<Integer> readMessage(@RequestParam Long id) {
        Integer count = messageService.readMessage(id);
        if (count == 1) {
            return new ResponseEntity<>(SystemConstantDefine.SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(SystemConstantDefine.FAIL, HttpStatus.OK);
    }

    @GetMapping("/getMessageCount")
    public ResponseEntity<Integer> getMessageCount(@RequestParam Long userId) {
        return new ResponseEntity<>(messageService.getMessageCount(userId), HttpStatus.OK);
    }

}
