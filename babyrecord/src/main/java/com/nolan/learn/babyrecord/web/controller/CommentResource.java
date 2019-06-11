package com.nolan.learn.babyrecord.web.controller;

import com.nolan.learn.babyrecord.model.vo.CommentInfoVO;
import com.nolan.learn.babyrecord.model.vo.ResponseModel;
import com.nolan.learn.babyrecord.service.CommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * description:
 *
 * @author zhun.huang 2019-06-07
 */
@RestController
@RequestMapping("api/comment")
public class CommentResource {

    @Resource
    private CommentService commentService;

    @PostMapping("add")
    public ResponseModel<Boolean> add(CommentInfoVO commentInfoVO) {
        try {
            return ResponseModel.success(commentService.add(commentInfoVO));
        } catch (Exception e) {
            return ResponseModel.fail(-1,e.getMessage());
        }
    }

    @RequestMapping("delete")
    public ResponseModel<Boolean> delete(Long id){
        try {
            return ResponseModel.success(commentService.delete(id));
        } catch (Exception e) {
            return ResponseModel.fail(-1,e.getMessage());
        }
    }

}
