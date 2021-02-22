package com.acm.clubManagementSystem.controller.authApi.award;

import com.acm.clubManagementSystem.entity.award.Award;
import com.acm.clubManagementSystem.service.award.AwardSummaryService;
import com.acm.clubManagementSystem.service.award.AwardTeamService;
import com.acm.clubManagementSystem.util.ResultData;
import com.acm.clubManagementSystem.vo.AwardSummaryScreenReqVo;
import com.acm.clubManagementSystem.vo.AwardTeamScreenReqVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName 获奖汇总报表模块
 * @Author lkh
 * @Date 2021/1/27 17:03
 * @Version 1.0
 */
@RestController
@RequestMapping("/awardSummary")
@Api(tags = "获奖汇总报表模块")
public class AwardSummary {
    @Autowired
    private AwardSummaryService service;

    /**
     * 团队获奖信息接口
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "团队获奖信息接口")
    public ResultData<PageInfo<Award>> selectScreen(AwardSummaryScreenReqVo vo) {
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
}
