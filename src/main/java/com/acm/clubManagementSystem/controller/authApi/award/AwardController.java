package com.acm.clubManagementSystem.controller.authApi.award;

import com.acm.clubManagementSystem.entity.award.Award;
import com.acm.clubManagementSystem.entity.team.Team;
import com.acm.clubManagementSystem.service.award.AwardService;
import com.acm.clubManagementSystem.service.team.TeamService;
import com.acm.clubManagementSystem.util.ResultData;
import com.acm.clubManagementSystem.vo.AwardScreenReqVo;
import com.acm.clubManagementSystem.vo.TeamScreenReqVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName 个人获奖模块
 * @Author lkh
 * @Date 2021/1/27 13:31
 * @Version 1.0
 */
@RestController
@RequestMapping("/award")
@Api(tags = "个人获奖模块")
public class AwardController {
    @Autowired
    private AwardService service;

    /**
     * 个人获奖信息接口
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "个人获奖信息接口")
    public ResultData<PageInfo<Award>> selectScreen(AwardScreenReqVo vo) {
        ResultData<PageInfo<Award>> result = ResultData.success();
        try {
            PageInfo<Award> entitys = service.selectScreen(vo);
            result.setData(entitys);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.fail(-1, e.getMessage());
        }
        return result;
    }

    /**
     * 个人获奖新增
     * @param award
     * @return
     */
    @PostMapping("/insert")
    @ApiOperation(value = "个人获奖新增接口")
    public ResultData insert(@RequestBody @Valid Award award){
        try {
            service.insert(award);
        } catch (Exception e) {
            return ResultData.fail(-1,e.getMessage());
        }
        return ResultData.success();
    }

    /**
     * 个人获奖编辑
     * @param award
     * @return
     */
    @PutMapping("/update")
    @ApiOperation(value="个人获奖辑接口")
    public ResultData update(@RequestBody @Valid Award award) {
        try {
            service.update(award);
        } catch (Exception e) {
            return ResultData.fail(-1,e.getMessage());
        }
        return ResultData.success();
    }


    /**
     * 个人获奖删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation(value="个人获奖删除接口")
    public ResultData delete(String  id) {
        try {
            service.delete(id);
        } catch (Exception e) {
            return ResultData.fail(-1,e.getMessage());
        }
        return ResultData.success();
    }
}
