package com.acm.clubManagementSystem.service.award;

import com.acm.clubManagementSystem.entity.award.Award;
import com.acm.clubManagementSystem.entity.student.Student;
import com.acm.clubManagementSystem.entity.team.Team;
import com.acm.clubManagementSystem.exception.BusinessException;
import com.acm.clubManagementSystem.mapper.award.AwardMapper;
import com.acm.clubManagementSystem.mapper.student.StudentMapper;
import com.acm.clubManagementSystem.mapper.team.TeamMapper;
import com.acm.clubManagementSystem.vo.AwardTeamScreenReqVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AwardTeamService {
    @Autowired
    private AwardMapper mapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeamMapper teamMapper;

    public PageInfo<Award> selectScreen(AwardTeamScreenReqVo vo) {
        PageHelper.startPage(vo.getPage(),vo.getLimit(),true);
//        根据查询条件
        Map<String, Object> param =getMap(vo);
        List<Award> awards=mapper.selectAwardTeamByCondition(param);
        for (Award award : awards) {
//            可能团队没有导师
            if (award.getMentorId()!=null){
                Student mentor = studentMapper.selectByMId(award.getMentorId());
                award.setMentorName(mentor.getName());
            }
            // 可能团队没有队长
            if (award.getSid()!=null){
                Student student = studentMapper.selectByPrimaryKey(award.getSid());
                award.setSname(student.getName());
            }
//            获取除了队长外的全部队员
            List<Student> students = studentMapper.selectByTId(award.getAssociatedId());
            List<String> list=new ArrayList<>();
            for (Student student : students) {
                if (!student.getId().equals(award.getSid())){
                    list.add(student.getName());
                }
            }
            award.setTeamMember(list);
        }
        return PageInfo.of(awards);
    }

    public Map<String, Object> getMap(AwardTeamScreenReqVo vo){
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
        if (StringUtils.isNotBlank(vo.getTeamName())) {
            param.put("teamName", "%"+vo.getTeamName()+"%");
        }
        if (StringUtils.isNotBlank(vo.getType())) {
            param.put("type", vo.getType());
        }
        if (StringUtils.isNotBlank(vo.getRaceName())) {
            param.put("raceName", vo.getRaceName());
        }
        if (StringUtils.isNotBlank(vo.getAwardGrade())) {
            param.put("awardGrade", vo.getAwardGrade());
        }
        if (StringUtils.isNotBlank(vo.getMentorId())) {
            param.put("mentorId", vo.getMentorId());
        }
        return param;
    }


    /**
     * 团队信息新增
     * @param award
     */
    @Transactional
    public void insert(Award award) {
        award.setId(UUID.randomUUID().toString());
        award.setFlag(1);
        Team team = teamMapper.selectByTeamName(award.getTeamName());
//        团队名称唯一，根据名称获取团队id
        if (team!=null){
            award.setAssociatedId(team.getId());
        }else{
            throw new BusinessException(-1, "新增失败：该团队不存在 "+award.getTeamName());
        }
        mapper.insert(award);
    }

    /**
     * 团队信息编辑
     * @param award
     */
    @Transactional
    public void update(Award award) {
        award.setFlag(1);
        Team team = teamMapper.selectByTeamName(award.getTeamName());
//        团队名称唯一，根据名称获取团队id
        if (team!=null){
            award.setAssociatedId(team.getId());
        }else{
            throw new BusinessException(-1, "新增失败：该团队不存在 "+award.getTeamName());
        }
        mapper.updateByPrimaryKey(award);
    }

    /**
     * 团队信息删除
     * @param id
     */
    @Transactional
    public void delete(String id) {
        mapper.deleteByPrimaryKey(id);
    }
}
