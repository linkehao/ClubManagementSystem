package com.acm.clubManagementSystem.service.mentor;

import com.acm.clubManagementSystem.entity.student.Student;
import com.acm.clubManagementSystem.mapper.student.StudentMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentorService {

    @Autowired
    private StudentMapper mapper;

    public PageInfo<Student> selectScreen() {
        List<Student> lists=mapper.selectMentors();
        return PageInfo.of(lists);
    }
}
