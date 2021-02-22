package com.acm.clubManagementSystem.service.student;

import com.acm.clubManagementSystem.entity.sr.Sr;
import com.acm.clubManagementSystem.entity.student.Student;
import com.acm.clubManagementSystem.entity.team.Team;
import com.acm.clubManagementSystem.entity.topic.Topic;
import com.acm.clubManagementSystem.exception.BusinessException;
import com.acm.clubManagementSystem.mapper.sr.SrMapper;
import com.acm.clubManagementSystem.mapper.student.StudentMapper;
import com.acm.clubManagementSystem.mapper.team.TeamMapper;
import com.acm.clubManagementSystem.mapper.topic.TopicMapper;
import com.acm.clubManagementSystem.service.topic.TopicService;
import com.acm.clubManagementSystem.vo.StudentScreenReqVo;
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


/**
 * @ClassName 学生管理模块
 * @Author lkh
 * @Date 2021/1/19 15:51
 * @Version 1.0
 */
@Service
public class StudentService {
    @Autowired
    private StudentMapper mapper;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private SrMapper srMapper;


    public PageInfo<Student> selectScreen(StudentScreenReqVo vo) {
        PageHelper.startPage(vo.getPage(),vo.getLimit(),true);
//        根据查询条件
        Map<String, Object> param =getMap(vo);
        List<Student> students=mapper.selectAllByCondition(param);
        for (Student student : students) {
            if (student.getMentorId()!=null&&!student.getMentorId().equals("")){
//                Mentor  = mentorMapper.selectByPrimaryKey();
                Student mentor=mapper.selectByMId(student.getMentorId());
                student.setMentorName(mentor.getName());
            }
            if (student.getTeamId()!=null&&!student.getTeamId().equals("")){
                Team team = teamMapper.selectByPrimaryKey(student.getTeamId());
                student.setTeamName(team.getTeamName());
            }
        }
        return PageInfo.of(students);
    }

    public Map<String, Object> getMap(StudentScreenReqVo vo){
        Map<String, Object> param =new HashMap<>();
        if (StringUtils.isNotBlank(vo.getStartTime())) {
            param.put("startTime",vo.getStartTime());
        }
        if (StringUtils.isNotBlank(vo.getEndTime())) {
            param.put("endTime",vo.getEndTime());
        }
        if (StringUtils.isNotBlank(vo.getSortBy())) {
            param.put("sortBy", vo.getSortBy());
        }
        if (StringUtils.isNotBlank(vo.getAscdesc())) {
            param.put("ascdesc", vo.getAscdesc());
        }
        if (StringUtils.isNotBlank(vo.getName())) {
            param.put("name", "%"+vo.getName()+"%");
        }
        if (StringUtils.isNotBlank(vo.getCollege())) {
            param.put("college", vo.getCollege());
        }
        if (StringUtils.isNotBlank(vo.getMentorId())) {
            param.put("mentorId", vo.getMentorId());
        }
        return param;
    }


    /**
     * 学生信息新增
     * @param student
     */
    @Transactional
    public void insert(Student student) {
//        student.setId(mapper.selectAll().size()+1+"");
        Student student1 = mapper.selectByPrimaryKey(student.getId());
        if (student1!=null){
            throw new BusinessException(-1, "新增失败：该学号已存在 "+student.getId());
        }
        try {
            student.setPassword("123456");
            student.setStatus(0);
//            新创建的学生默认是普通用户级别
            Sr sr = new Sr();
            sr.setId(UUID.randomUUID().toString());
            sr.setSid(student.getId());
            sr.setRid("2");
            srMapper.insert(sr);
            mapper.insert(student);
        }catch (Exception e){
            throw new BusinessException(-1, "失败原因：系统繁忙：");
        }

    }

    /**
     * 学生信息编辑
     * @param student
     */
    @Transactional
    public void update(Student student) {
        Student student1 = mapper.selectByPrimaryKey(student.getId());
//        如果修改了所属团队，
//        如果本来就没有队伍，那么直接更新
        if (student1.getTeamId()==null||!student1.getTeamId().equals(student.getTeamId())){
            // 查看该学生是否已是某团队的队长，是则把那队的队长置null，因为该学生已变成其他队的队员了
            Team team=teamMapper.selectBySId(student.getId());
            if (team!=null){
                team.setSid(null);
                teamMapper.updateByPrimaryKey(team);
            }
        }
        if ("".equals(student.getTeamId()))
            student.setTeamId(null);
        if ("".equals(student.getMentorId()))
            student.setTeamId(null);
        try {
            student.setStatus(0);
            mapper.updateByPrimaryKey(student);
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException(-1, "失败原因：系统繁忙：");
        }
    }

    /**
     * 学生信息删除
     * @param id
     */
    @Transactional
    public void delete(String id) {
        try {
            Team team = teamMapper.selectBySId(id);
            if (team!=null){
                team.setSid(null);
                teamMapper.updateByPrimaryKey(team);
            }
//            删除时，把该学生创建的题目和对应的评论也删除
            List<Topic> topics=topicMapper.selectBySId(id);
            for (Topic topic : topics) {
                topicService.delete(topic.getId());
            }
//            删除时，把角色关联也删除
            Sr sr = srMapper.selectBySId(id);
            srMapper.deleteByPrimaryKey(sr.getId());
            mapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            throw new BusinessException(-1, "失败原因：系统繁忙：");
        }
    }
}
