package com.acm.clubManagementSystem.service.userManage;

import com.acm.clubManagementSystem.entity.sr.Sr;
import com.acm.clubManagementSystem.entity.student.Student;
import com.acm.clubManagementSystem.entity.team.Team;
import com.acm.clubManagementSystem.exception.BusinessException;
import com.acm.clubManagementSystem.mapper.sr.SrMapper;
import com.acm.clubManagementSystem.mapper.student.StudentMapper;
import com.acm.clubManagementSystem.mapper.team.TeamMapper;
import com.acm.clubManagementSystem.service.student.StudentService;
import com.acm.clubManagementSystem.vo.StudentScreenReqVo;
import com.acm.clubManagementSystem.vo.UserManegeScreenReqVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserManegeService {

    @Autowired
    private StudentMapper mapper;

    @Autowired
    private SrMapper srMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private StudentService service;

    /**
     * 查询用户信息接口
     * @param vo
     * @return
     */
    public PageInfo<Student> selectScreen(UserManegeScreenReqVo vo) {
        PageHelper.startPage(vo.getPage(),vo.getLimit(),true);
//        根据查询条件
        Map<String, Object> param =getMap(vo);
        List<Student> students=mapper.selectAllByUserInfo(param);
        return PageInfo.of(students);
    }

    public Map<String, Object> getMap(UserManegeScreenReqVo vo){
        Map<String, Object> param =new HashMap<>();
        if (StringUtils.isNotBlank(vo.getName())) {
            param.put("name", "%"+vo.getName()+"%");
        }
        if (StringUtils.isNotBlank(vo.getId())) {
            param.put("id", "%"+vo.getId()+"%");
        }
        if (StringUtils.isNotBlank(vo.getRid())) {
            param.put("rid", vo.getRid());
        }
        return param;
    }

    /**
     * 用户信息新增 (只能添加管理员级别的用户)
     * @param student
     * @return
     */
    @Transactional
    public void insert(Student student) {
        Student student1 = mapper.selectByPrimaryKey(student.getId());
        if (student1!=null){
            throw new BusinessException(-1, "新增失败：该学号已存在 "+student.getId());
        }
        student.setStatus(1);
        mapper.insert(student);
        Sr sr=new Sr();
        sr.setId(UUID.randomUUID().toString());
        sr.setSid(student.getId());
        sr.setRid("1");
        srMapper.insert(sr);
    }

    /**
     * 用户信息编辑
     * @param student
     * @return
     */
    @Transactional
    public void update(Student student) {
        Student student1 = mapper.selectByPrimaryKey(student.getId());
        Sr sr = srMapper.selectBySId(student.getId());
        sr.setRid(student.getRid());
        srMapper.updateByPrimaryKey(sr);
        student.setStatus(student1.getStatus());
        mapper.updateByPrimaryKeySelective(student);
    }

    /**
     * 用户信息删除
     * @param id
     * @return
     */
    @Transactional
    public void delete(String id) {
        Student student = mapper.selectByPrimaryKey(id);
        if (student.getStatus()==0){
            service.delete(id);
        }else{
            List<Student> students = mapper.selectByMentorId(id);
            for (Student student1 : students) {
                student1.setMentorId(null);
                mapper.updateByPrimaryKey(student1);
            }
            List<Team> teams=teamMapper.selectByMentorId(id);
            for (Team team : teams) {
                team.setMentorId(null);
                teamMapper.updateByPrimaryKey(team);
            }
            //            删除时，把角色关联也删除
            Sr sr = srMapper.selectBySId(id);
            srMapper.deleteByPrimaryKey(sr.getId());
            mapper.deleteByPrimaryKey(id);
        }
    }
}
