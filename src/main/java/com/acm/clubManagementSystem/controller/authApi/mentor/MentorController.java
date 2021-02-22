package com.acm.clubManagementSystem.controller.authApi.mentor;

import com.acm.clubManagementSystem.entity.student.Student;
import com.acm.clubManagementSystem.service.mentor.MentorService;
import com.acm.clubManagementSystem.util.ResultData;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName 学生信息模块
 * @Author lkh
 * @Date 2021/1/26 16:45
 * @Version 1.0
 */
@RestController
@RequestMapping("/mentor")
@Api(tags = "导师信息模块")
class MentorController {
    @Autowired
    private MentorService service;

    /**
     * 查询导师信息接口
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询导师信息接口")
    public ResultData<PageInfo<Student>> selectScreen() {
        ResultData<PageInfo<Student>> result = ResultData.success();
        try {
            PageInfo<Student> entitys = service.selectScreen();
            result.setData(entitys);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.fail(-1, e.getMessage());
        }
        return result;
    }
}
