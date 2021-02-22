package com.acm.clubManagementSystem.controller.authApi.comment;

import com.acm.clubManagementSystem.entity.award.Award;
import com.acm.clubManagementSystem.entity.comment.Comment;
import com.acm.clubManagementSystem.entity.topic.Topic;
import com.acm.clubManagementSystem.service.award.AwardService;
import com.acm.clubManagementSystem.service.comment.CommentService;
import com.acm.clubManagementSystem.util.ResultData;
import com.acm.clubManagementSystem.vo.CommentScreenReqVo;
import com.acm.clubManagementSystem.vo.TopicScreenReqVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName 评论模块
 * @Author lkh
 * @Date 2021/1/29 14:37
 * @Version 1.0
 */
@RestController
@RequestMapping("/comment")
@Api(tags = "评论模块")
public class CommentController {

    @Autowired
    private CommentService service;

    /**
     * 查询题目的评论信息接口
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询题目的评论信息接口")
    public ResultData<PageInfo<Comment>> selectScreen(CommentScreenReqVo vo) {
        ResultData<PageInfo<Comment>> result = ResultData.success();
        try {
            PageInfo<Comment> entitys = service.selectScreen(vo);
            result.setData(entitys);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.fail(-1, e.getMessage());
        }
        return result;
    }

    /**
     * 评论新增
     * @param comment
     * @return
     */
    @PostMapping("/insert")
    @ApiOperation(value = "评论新增接口")
    public ResultData insert(@RequestBody @Valid Comment comment){
        try {
            service.insert(comment);
        } catch (Exception e) {
            return ResultData.fail(-1,e.getMessage());
        }
        return ResultData.success();
    }

}
