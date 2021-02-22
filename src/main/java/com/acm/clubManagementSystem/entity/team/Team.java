package com.acm.clubManagementSystem.entity.team;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Team implements Serializable {
    private String id;

    private String teamName;

    private String sid;

    private String sname;

    private String mentorId;

    private String mentorName;

    private List<String> teamMember;

    private Integer total;

    private static final long serialVersionUID = 1L;
}