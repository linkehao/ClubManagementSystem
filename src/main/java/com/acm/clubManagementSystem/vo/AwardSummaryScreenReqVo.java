package com.acm.clubManagementSystem.vo;

import com.acm.clubManagementSystem.vo.commonVo.PageBaseVo;
import lombok.Data;

/**
 * @ClassName 获奖汇总报表接收对象
 * @Author lkh
 * @Date 2021/1/27 18:26
 * @Version 1.0
 */
@Data
public class AwardSummaryScreenReqVo extends PageBaseVo{
    private String startTime;   //开始时间
    private String endTime; //结束时间
    private String type;    //级别
    private String raceName;    //奖项名
    private String awardGrade;    //等级
}
