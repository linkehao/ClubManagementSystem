package com.acm.clubManagementSystem.controller.publicApi.user;


import com.acm.clubManagementSystem.entity.student.Student;
import com.acm.clubManagementSystem.entity.user.User;
import com.acm.clubManagementSystem.service.user.UserService;
import com.acm.clubManagementSystem.util.JwtUtil;
import com.acm.clubManagementSystem.util.ResponseContants;
import com.acm.clubManagementSystem.util.ResultData;
import com.acm.clubManagementSystem.vo.VoToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @ClassName 用户管理模块
 * @Author lkh
 * @Date 2021/1/18 11:28
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理模块")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录接口
     * @param user
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登录接口")
    public ResultData login(@RequestBody User user){
        String token=null;
        try {
            boolean verify = userService.verify(user);
            if (verify){
                token = JwtUtil.sign(user.getUsername(),user.getPassword());
                userService.saveToken(user,token);
                return ResultData.success(ResponseContants.SUCCESS,ResponseContants.SUCCESS_MESSAGE,new VoToken(token));
            }else{
                return ResultData.fail(ResponseContants.FAIL,ResponseContants.FAIL_MESSAGE+"用户名和密码不匹配");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.fail(ResponseContants.FAIL,ResponseContants.FAIL_MESSAGE+"用户名和密码不匹配");
        }
    }

    /**
     * 获取用户信息接口
     * @param token
     * @return
     */
    @GetMapping("/info")
    @ApiOperation(value = "获取用户信息接口")
    public ResultData info(@RequestParam("token") String token){
        try {
            Student user=userService.selectInfo(token);
            if (user!=null){
                boolean verify = JwtUtil.verify(token, user.getId(), user.getPassword());
                if (verify){
                    HashMap<String, Object> responseData = new HashMap<>();
//                    responseData.put("role","admin");
//                    responseData.put("name","lin");
                    responseData.put("role",user.getRole());
                    responseData.put("name",user.getId());
                    responseData.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
                    return ResultData.success(ResponseContants.SUCCESS,ResponseContants.SUCCESS_MESSAGE,responseData);
                }else{
                    return ResultData.fail(ResponseContants.FAIL,ResponseContants.FAIL_MESSAGE);
                }
            }else{
                return ResultData.fail(ResponseContants.FAIL,ResponseContants.FAIL_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.fail(ResponseContants.FAIL,ResponseContants.FAIL_MESSAGE);
        }
    }

    /**
     * 用户退出接口
     * @param token
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation(value = "用户退出接口")
    public ResultData logout(@RequestHeader("X-Token") String token){
        Student user=userService.selectInfo(token);
        try {
            boolean verify = JwtUtil.verify(token, user.getId(), user.getPassword());
            if (verify){
                userService.deleteToken(user);
                return ResultData.success(ResponseContants.SUCCESS,"logout success",null);
            }else{
                return ResultData.fail(ResponseContants.FAIL,ResponseContants.FAIL_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.fail(ResponseContants.FAIL,ResponseContants.FAIL_MESSAGE);
        }
    }

}
