package com.acm.clubManagementSystem.controller.authApi.student;

import com.acm.clubManagementSystem.entity.student.Student;

import com.acm.clubManagementSystem.service.student.StudentService;
import com.acm.clubManagementSystem.util.ResultData;
import com.acm.clubManagementSystem.vo.StudentScreenReqVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName 学生信息模块
 * @Author lkh
 * @Date 2021/1/19 15:50
 * @Version 1.0
 */
@RestController
@RequestMapping("/student")
@Api(tags = "学生信息模块")
public class StudentController {
    @Autowired
    private StudentService service;

    /**
     * 查询学生信息接口
     * @param vo
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询学生信息接口")
    public ResultData<PageInfo<Student>> selectScreen(StudentScreenReqVo vo) {
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
     * 学生信息新增
     * @param student
     * @return
     */
    @PostMapping("/insert")
    @ApiOperation(value = "学生信息新增接口")
    public ResultData insert(@RequestBody @Valid Student student){
        try {
            service.insert(student);
        } catch (Exception e) {
            return ResultData.fail(-1,e.getMessage());
        }
        return ResultData.success();
    }

    /**
     * 学生信息编辑
     * @param student
     * @return
     */
    @PutMapping("/update")
    @ApiOperation(value="学生信息编辑接口")
    public ResultData update(@RequestBody @Valid Student student) {
        try {
            service.update(student);
        } catch (Exception e) {
            return ResultData.fail(-1,e.getMessage());
        }
        return ResultData.success();
    }


    /**
     * 学生信息删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation(value="学生信息删除接口")
    public ResultData delete(String  id) {
        try {
            service.delete(id);
        } catch (Exception e) {
            return ResultData.fail(-1,e.getMessage());
        }
        return ResultData.success();
    }

//    public ResultData info(@RequestParam("token") String token){
//        try {
//            service.select();
//            return ResultData.success(ResponseContants.SUCCESS,ResponseContants.SUCCESS_MESSAGE,responseData);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResultData.fail(ResponseContants.FAIL,ResponseContants.FAIL_MESSAGE);
//        }
//    }

}
