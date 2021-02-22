package com.acm.clubManagementSystem.controller.authApi.userManage;

import com.acm.clubManagementSystem.entity.student.Student;
import com.acm.clubManagementSystem.service.student.StudentService;
import com.acm.clubManagementSystem.service.userManage.UserManegeService;
import com.acm.clubManagementSystem.util.ResultData;
import com.acm.clubManagementSystem.vo.StudentScreenReqVo;
import com.acm.clubManagementSystem.vo.UserManegeScreenReqVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName 用户管理模块
 * @Author lkh
 * @Date 2021/1/29 16:30
 * @Version 1.0
 */
@RestController
@RequestMapping("/userManage")
@Api(tags = "用户管理模块")
public class UserManageController {
    @Autowired
    private UserManegeService service;

    /**
     * 查询用户信息接口
     * @param vo
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询用户信息接口")
    public ResultData<PageInfo<Student>> selectScreen(UserManegeScreenReqVo vo) {
        ResultData<PageInfo<Student>> result = ResultData.success();
        try {
            PageInfo<Student> entitys = service.selectScreen(vo);
            result.setData(entitys);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.fail(-1, e.getMessage());
        }
        return result;
    }

    /**
     * 用户信息新增
     * @param student
     * @return
     */
    @PostMapping("/insert")
    @ApiOperation(value = "用户信息新增接口")
    public ResultData insert(@RequestBody @Valid Student student){
        try {
            service.insert(student);
        } catch (Exception e) {
            return ResultData.fail(-1,e.getMessage());
        }
        return ResultData.success();
    }

    /**
     * 用户信息编辑
     * @param student
     * @return
     */
    @PutMapping("/update")
    @ApiOperation(value="用户信息编辑接口")
    public ResultData update(@RequestBody @Valid Student student) {
        try {
            service.update(student);
        } catch (Exception e) {
            return ResultData.fail(-1,e.getMessage());
        }
        return ResultData.success();
    }


    /**
     * 用户信息删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation(value="用户信息删除接口")
    public ResultData delete(String  id) {
        try {
            service.delete(id);
        } catch (Exception e) {
            return ResultData.fail(-1,e.getMessage());
        }
        return ResultData.success();
    }
}
