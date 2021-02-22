package com.acm.clubManagementSystem.service.user;


import com.acm.clubManagementSystem.entity.role.Role;
import com.acm.clubManagementSystem.entity.sr.Sr;
import com.acm.clubManagementSystem.entity.student.Student;
import com.acm.clubManagementSystem.entity.user.User;
import com.acm.clubManagementSystem.mapper.role.RoleMapper;
import com.acm.clubManagementSystem.mapper.sr.SrMapper;
import com.acm.clubManagementSystem.mapper.student.StudentMapper;

import com.acm.clubManagementSystem.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName
 * @Author lkh
 * @Date 2021/1/18 11:47
 * @Version 1.0
 */
@Service
public class UserService {

    @Autowired
    private SrMapper srMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private StudentMapper mapper;

    public boolean verify(User user) {
//        根据用户名查找用户信息
       Student user1 = mapper.selectByPrimaryKey(user.getUsername());
       if (user1!=null&&user1.getPassword().equals(user.getPassword())){
           return true;
       }
       return false;
    }

    public void saveToken(User user,String token) {
//        根据用户名查找用户信息
        Student user1 = mapper.selectByPrimaryKey(user.getUsername());
        user1.setSalt(token);
        mapper.updateByPrimaryKey(user1);
    }

    public Student selectInfo(String token) {
        Student user=mapper.selectByToken(token);
        Sr sr= srMapper.selectBySId(user.getId());
        List<String> list=new ArrayList<>();
        Role role = roleMapper.selectByPrimaryKey(sr.getRid());
        user.setRole(role.getRole());
        return user;
    }

    public void deleteToken(Student user) {
        //        根据用户名查找用户信息
        user.setSalt(null);
        mapper.updateByPrimaryKey(user);
    }
}
