package com.acm.clubManagementSystem.vo;

import com.acm.clubManagementSystem.vo.commonVo.PageBaseVo;
import lombok.Data;

/**
 * @ClassName 团队获奖信息接收对象
 * @Author lkh
 * @Date 2021/1/27 13:35
 * @Version 1.0
 */
@Data
public class AwardTeamScreenReqVo extends PageBaseVo {
    private String teamName;    //团队名称
    private String type;    //级别
    private String raceName;    //奖项名
    private String awardGrade;    //等级
    private String startTime;   //开始时间
    private String endTime; //结束时间
    private String mentorId;     //导师
}
