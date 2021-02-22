package com.acm.clubManagementSystem.vo;

import com.acm.clubManagementSystem.vo.commonVo.PageBaseVo;
import lombok.Data;

/**
 * @ClassName 团队接收对象
 * @Author lkh
 * @Date 2021/1/27 21:03
 * @Version 1.0
 */
@Data
public class TeamScreenReqVo extends PageBaseVo {
    private String teamName;    //队名
    private String mentorId;     //导师
}
