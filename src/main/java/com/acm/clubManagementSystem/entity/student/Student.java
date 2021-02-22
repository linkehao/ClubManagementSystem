package com.acm.clubManagementSystem.entity.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "Student",description = "学生信息对象")
public class Student implements Serializable {
    /**
     * 唯一主键
     */
    @ApiModelProperty(value="ID",name="id")
    private String id;
    /**
     * 密码
     */
    @ApiModelProperty(value="密码",name="password")
    private String password;
    /**
     * token
     */
    @ApiModelProperty(value="token",name="salt")
    private String salt;
    /**
     * 所属角色
     */
    @ApiModelProperty(value="所属角色",name="role")
    private String role;
    /**
     * 角色id
     */
    @ApiModelProperty(value="角色id",name="rid")
    private String rid;
    /**
     * 姓名
     */
    @ApiModelProperty(value="姓名",name="name")
    private String name;
    /**
     * 年龄
     */
    @ApiModelProperty(value="年龄",name="age")
    private Integer age;
    /**
     * 性别
     */
    @ApiModelProperty(value="性别",name="sex")
    private String sex;
    /**
     * 年级
     */
    @ApiModelProperty(value="年级",name="grade")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date grade;
    /**
     * 学院
     */
    @ApiModelProperty(value="学院",name="college")
    private String college;
    /**
     * 导师id
     */
    @ApiModelProperty(value="导师id",name="mentorId")
    private String mentorId;
    /**
     * 导师姓名
     */
    @ApiModelProperty(value="导师姓名",name="mentorName")
    private String mentorName;
    /**
     * 团队id
     */
    @ApiModelProperty(value="团队id",name="teamId")
    private String teamId;
    /**
     * 团队名称
     */
    @ApiModelProperty(value="团队名称",name="teamName")
    private String teamName;
    /**
     * 获奖总数
     */
    @ApiModelProperty(value="获奖总数",name="total")
    private Integer total;
    /**
     * 0为学生 1为老师
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}