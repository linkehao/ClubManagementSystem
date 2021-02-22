package com.acm.clubManagementSystem.vo;

import com.acm.clubManagementSystem.vo.commonVo.PageBaseVo;
import lombok.Data;

/**
 * @ClassName 学生管理接收对象
 * @Author lkh
 * @Date 2021/1/19 16:03
 * @Version 1.0
 */
@Data
public class StudentScreenReqVo extends PageBaseVo {
    private String name;    //姓名
    private String startTime;   //开始时间
    private String endTime; //结束时间
    private String college;     //学院
    private String mentorId;     //导师

}
