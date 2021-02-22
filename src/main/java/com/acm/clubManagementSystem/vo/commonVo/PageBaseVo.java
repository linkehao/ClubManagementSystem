package com.acm.clubManagementSystem.vo.commonVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName 接收参数基类
 * @Author lkh
 * @Date 2021/1/19 16:04
 * @Version 1.0
 */
@Data
public class PageBaseVo {
    private int page;//页码
    private int limit;//每页记录数
    @ApiModelProperty(value="排序字段",name="sortBy")
    private String sortBy;//排序字段
    @ApiModelProperty(value="升序asc或降序desc",name="ascdesc")
    private String ascdesc;//升序或降序
}