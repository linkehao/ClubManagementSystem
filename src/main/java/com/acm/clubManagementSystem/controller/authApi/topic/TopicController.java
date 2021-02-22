package com.acm.clubManagementSystem.controller.authApi.topic;

import com.acm.clubManagementSystem.entity.team.Team;
import com.acm.clubManagementSystem.entity.topic.Topic;
import com.acm.clubManagementSystem.service.team.TeamService;
import com.acm.clubManagementSystem.service.topic.TopicService;
import com.acm.clubManagementSystem.util.ResultData;
import com.acm.clubManagementSystem.vo.TeamScreenReqVo;
import com.acm.clubManagementSystem.vo.TopicScreenReqVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName 题目信息模块
 * @Author lkh
 * @Date 2021/1/28 13:03
 * @Version 1.0
 */
@RestController
@RequestMapping("/topic")
@Api(tags = "题目信息模块")
public class TopicController {

    @Autowired
    private TopicService service;

    /**
     * 查询题目信息接口
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询题目信息接口")
    public ResultData<PageInfo<Topic>> selectScreen(TopicScreenReqVo vo) {
        ResultData<PageInfo<Topic>> result = ResultData.success();
        try {
            PageInfo<Topic> entitys = service.selectScreen(vo);
            result.setData(entitys);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.fail(-1, e.getMessage());
        }
        return result;
    }

    /**
     * 题目信息新增
     * @param topic
     * @return
     */
    @PostMapping("/insert")
    @ApiOperation(value = "题目信息新增接口")
    public ResultData insert(@RequestBody @Valid Topic topic){
        try {
            service.insert(topic);
        } catch (Exception e) {
            return ResultData.fail(-1,e.getMessage());
        }
        return ResultData.success();
    }

    /**
     * 题目信息编辑
     * @param topic
     * @return
     */
    @PutMapping("/update")
    @ApiOperation(value="题目信息编辑接口")
    public ResultData update(@RequestBody @Valid Topic topic) {
        try {
            service.update(topic);
        } catch (Exception e) {
            return ResultData.fail(-1,e.getMessage());
        }
        return ResultData.success();
    }


    /**
     * 题目信息删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation(value="题目信息删除接口")
    public ResultData delete(String  id) {
        try {
            service.delete(id);
        } catch (Exception e) {
            return ResultData.fail(-1,e.getMessage());
        }
        return ResultData.success();
    }
}
