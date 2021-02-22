package com.acm.clubManagementSystem.vo;

import com.acm.clubManagementSystem.vo.commonVo.PageBaseVo;
import lombok.Data;

/**
 * @ClassName 用户管理接收对象
 * @Author lkh
 * @Date 2021/1/29 16:37
 * @Version 1.0
 */
@Data
public class UserManegeScreenReqVo extends PageBaseVo {
    private String id;    //账号
    private String name;   //姓名
    private String rid;   //角色
}
