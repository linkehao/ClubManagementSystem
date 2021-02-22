package com.acm.clubManagementSystem.interceptors;

import com.acm.clubManagementSystem.entity.student.Student;
import com.acm.clubManagementSystem.exception.BusinessException;
import com.acm.clubManagementSystem.mapper.student.StudentMapper;
import com.acm.clubManagementSystem.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTInterceptor implements HandlerInterceptor {

    @Autowired
    private StudentMapper mapper;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String token = request.getHeader("X-Token");
        try{
            Student user=mapper.selectByToken(token);
            boolean verify = JwtUtil.verify(token, user.getId(), user.getPassword());
            return verify;
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException(-1, "token认证失败失败 "+token);
        }
    }

}