package com.acm.clubManagementSystem.mapper.student;

import com.acm.clubManagementSystem.entity.student.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StudentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    List<Student> selectAllByCondition(Map<String, Object> param);

    List<Student> selectByTId(String tid);

//    按导师id来查找   跟学生表的id匹配
    Student selectByMId(String mentorId);

    List<Student> selectMentors();

    Student selectByToken(String token);

//    用户管理模块的  查询
    List<Student> selectAllByUserInfo(Map<String, Object> param);
    //    按导师id来查找学生集合
    List<Student> selectByMentorId(String mentorId);
}