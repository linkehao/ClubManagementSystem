package com.acm.clubManagementSystem.vo;

import com.acm.clubManagementSystem.vo.commonVo.PageBaseVo;
import lombok.Data;

/**
 * @ClassName 评论接收对象
 * @Author lkh
 * @Date 2021/1/19 16:03
 * @Version 1.0
 */
@Data
public class CommentScreenReqVo extends PageBaseVo {
    private String tid; //题目id
    private String startTime;   //开始时间
    private String endTime; //结束时间
}
