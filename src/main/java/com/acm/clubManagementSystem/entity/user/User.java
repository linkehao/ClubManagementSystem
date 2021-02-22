package com.acm.clubManagementSystem.entity.user;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class User implements Serializable {
    private String username;

    private String password;

    private static final long serialVersionUID = 1L;

}