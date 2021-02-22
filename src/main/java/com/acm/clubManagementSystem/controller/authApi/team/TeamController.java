package com.acm.clubManagementSystem.controller.authApi.team;

import com.acm.clubManagementSystem.entity.team.Team;
import com.acm.clubManagementSystem.service.mentor.MentorService;
import com.acm.clubManagementSystem.service.team.TeamService;
import com.acm.clubManagementSystem.util.ResultData;
import com.acm.clubManagementSystem.vo.StudentScreenReqVo;
import com.acm.clubManagementSystem.vo.TeamScreenReqVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName 团队信息模块
 * @Author lkh
 * @Date 2021/1/26 17:03
 * @Version 1.0
 */
@RestController
@RequestMapping("/team")
@Api(tags = "团队信息模块")
public class TeamController {
    @Autowired
    private TeamService service;

    /**
     * 查询团队信息接口
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询团队信息接口")
    public ResultData<PageInfo<Team>> selectScreen(TeamScreenReqVo vo) {
        ResultData<PageInfo<Team>> result = ResultData.success();
        try {
            PageInfo<Team> entitys = service.selectScreen(vo);
            result.setData(entitys);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.fail(-1, e.getMessage());
        }
        return result;
    }

    /**
     * 团队信息新增
     * @param team
     * @return
     */
    @PostMapping("/insert")
    @ApiOperation(value = "团队信息新增接口")
    public ResultData insert(@RequestBody @Valid Team team){
        try {
            service.insert(team);
        } catch (Exception e) {
            return ResultData.fail(-1,e.getMessage());
        }
        return ResultData.success();
    }

    /**
     * 团队信息编辑
     * @param team
     * @return
     */
    @PutMapping("/update")
    @ApiOperation(value="团队信息编辑接口")
    public ResultData update(@RequestBody @Valid Team team) {
        try {
            service.update(team);
        } catch (Exception e) {
            return ResultData.fail(-1,e.getMessage());
        }
        return ResultData.success();
    }


    /**
     * 团队信息删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation(value="团队信息删除接口")
    public ResultData delete(String  id) {
        try {
            service.delete(id);
        } catch (Exception e) {
            return ResultData.fail(-1,e.getMessage());
        }
        return ResultData.success();
    }

}
