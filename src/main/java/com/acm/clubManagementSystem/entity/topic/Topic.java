package com.acm.clubManagementSystem.entity.topic;

import com.acm.clubManagementSystem.entity.comment.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Topic implements Serializable {
    private String id;

    private String sid;

    private String topicTitle;

    private String difficultyLevel;
    /**
     * 创建者的姓名
     */
    private String name;
    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间",name="createTime")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date createTime;

    private String topicContent;

    private List<Comment> commentList;

    private static final long serialVersionUID = 1L;
}