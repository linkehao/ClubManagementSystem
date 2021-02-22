package com.acm.clubManagementSystem.entity.role;

import lombok.Data;

import java.io.Serializable;

@Data
public class Role implements Serializable {
    private String id;

    private String role;

    private static final long serialVersionUID = 1L;
}