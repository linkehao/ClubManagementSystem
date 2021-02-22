package com.acm.clubManagementSystem.entity.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Comment implements Serializable {
    private String id;

    private String sid;

    private String sname;

    private String tid;
    /**
     * 评论时间
     */
    @ApiModelProperty(value="评论时间",name="commentTime")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date commentTime;

    private String comment;

    private static final long serialVersionUID = 1L;
}