package com.acm.clubManagementSystem.entity.sr;

import lombok.Data;

import java.io.Serializable;

@Data
public class Sr implements Serializable {
    private String id;

    private String sid;

    private String rid;

    private static final long serialVersionUID = 1L;
}