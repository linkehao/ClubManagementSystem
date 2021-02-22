package com.acm.clubManagementSystem.service.team;


import com.acm.clubManagementSystem.entity.student.Student;
import com.acm.clubManagementSystem.entity.team.Team;
import com.acm.clubManagementSystem.exception.BusinessException;
import com.acm.clubManagementSystem.mapper.student.StudentMapper;
import com.acm.clubManagementSystem.mapper.team.TeamMapper;
import com.acm.clubManagementSystem.vo.TeamScreenReqVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TeamService {
    @Autowired
    private TeamMapper mapper;

    @Autowired
    private StudentMapper studentMapper;


    public PageInfo<Team> selectScreen(TeamScreenReqVo vo) {
        PageHelper.startPage(vo.getPage(),vo.getLimit(),true);
//        根据查询条件
        Map<String, Object> param =getMap(vo);
        List<Team> teams=mapper.selectAllByCondition(param);
        for (Team team : teams) {
            if (team.getSid()!=null){
                Student student = studentMapper.selectByPrimaryKey(team.getSid());
                team.setSname(student.getName());
            }
            Student mentor = studentMapper.selectByMId(team.getMentorId());
            team.setMentorName(mentor.getName());
        }
//        查找队伍的队员
        for (Team team : teams) {
            List<Student> students = studentMapper.selectByTId(team.getId());
            List<String> list=new ArrayList<>();
            for (Student student : students) {
                if (!student.getId().equals(team.getSid())){
                    list.add(student.getName());
                }
            }
            team.setTeamMember(list);
        }
        return PageInfo.of(teams);
    }

    public Map<String, Object> getMap(TeamScreenReqVo vo){
        Map<String, Object> param =new HashMap<>();
        if (StringUtils.isNotBlank(vo.getSortBy())) {
            param.put("sortBy", vo.getSortBy());
        }
        if (StringUtils.isNotBlank(vo.getAscdesc())) {
            param.put("ascdesc", vo.getAscdesc());
        }
        if (StringUtils.isNotBlank(vo.getTeamName())) {
            param.put("teamName", "%"+vo.getTeamName()+"%");
        }
        if (StringUtils.isNotBlank(vo.getMentorId())) {
            param.put("mentorId", vo.getMentorId());
        }
        return param;
    }


    /**
     * 团队信息新增
     * @param team
     */
    @Transactional
    public void insert(Team team) {
        team.setId(UUID.randomUUID().toString());
//       队名不能重复
        Team team1 = mapper.selectByTeamName(team.getTeamName());
        if (team1!=null){
            throw new BusinessException(-1, "新增失败：该队名已存在 "+team.getTeamName());
        }
//       判断该学号是否存在
        Student student = studentMapper.selectByPrimaryKey(team.getSid());
        if (student!=null){
            // 判断它是否为其他队的队长
            Team team3 = mapper.selectBySId(student.getId());
            if (team3!=null&&!team3.equals(team.getId())){
                team3.setSid(null);
                mapper.updateByPrimaryKey(team3);
            }
            // 无论该队长是否已是某队的成员，都要变成该队伍的队长
            student.setTeamId(team.getId());
            studentMapper.updateByPrimaryKey(student);
            mapper.insert(team);
        }else{
            throw new BusinessException(-1, "新增失败：该学号不存在  "+team.getSid());
        }

    }

    /**
     * 团队信息编辑
     * @param team
     */
    @Transactional
    public void update(Team team) {
//        队名不能重复
        Team team1 = mapper.selectByTeamName(team.getTeamName());
        if (team1!=null&&!team1.getId().equals(team.getId())){
            throw new BusinessException(-1, "修改失败：该队名已存在 "+team.getTeamName());
        }else{
            Team team2 = mapper.selectByPrimaryKey(team.getId());
//               把修改前的队长的teamid置null，相当于提出了当前队伍
            Student student1 = studentMapper.selectByPrimaryKey(team2.getSid());
            if (student1!=null){
                student1.setTeamId(null);
                studentMapper.updateByPrimaryKey(student1);
            }

            // 判断该学号是否存在
            Student student = studentMapper.selectByPrimaryKey(team.getSid());
            if (student!=null){
//                判断它是否为其他队的队长
                Team team3 = mapper.selectBySId(student.getId());
                if (team3!=null&&!team3.equals(team.getId())){
                    team3.setSid(null);
                    mapper.updateByPrimaryKey(team3);
                }
                // 无论该队长是否已是某队的成员，都要变成该队伍的队长
                student.setTeamId(team.getId());
                studentMapper.updateByPrimaryKey(student);
                mapper.updateByPrimaryKey(team);

            }else{
                throw new BusinessException(-1, "修改失败：该学号不存在  "+team.getSid());
            }
        }
    }

    /**
     * 团队信息删除
     * @param id
     */
    @Transactional
    public void delete(String id) {
        Team team = mapper.selectByPrimaryKey(id);
        List<Student> students=studentMapper.selectByTId(team.getId());
        for (Student student : students) {
            student.setTeamId(null);
            studentMapper.updateByPrimaryKey(student);
        }
        mapper.deleteByPrimaryKey(id);
    }

}
