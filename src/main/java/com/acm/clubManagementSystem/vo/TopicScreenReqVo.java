package com.acm.clubManagementSystem.vo;

import com.acm.clubManagementSystem.vo.commonVo.PageBaseVo;
import lombok.Data;

/**
 * @ClassName 题目接收对象
 * @Author lkh
 * @Date 2021/1/28 13:10
 * @Version 1.0
 */
@Data
public class TopicScreenReqVo extends PageBaseVo {
    private String name;    //创建者
    private String startTime;   //开始时间
    private String endTime; //结束时间
    private String difficultyLevel;     //难易度
}
