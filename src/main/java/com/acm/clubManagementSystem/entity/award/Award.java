package com.acm.clubManagementSystem.entity.award;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Award implements Serializable {
    private String id;

    private String associatedId;

    private String type;

    private String raceName;

    private String awardGrade;

    /**
     * 个人的姓名  或  团队的队长名
     */
    private String sname;

    private String sid;

    private String teamName;

    private String mentorId;

    private String mentorName;

    private List<String> teamMember;



    @ApiModelProperty(value="获奖时间",name="awardTime")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date awardTime;

    private Integer flag;

    private Integer total;

    private static final long serialVersionUID = 1L;
}