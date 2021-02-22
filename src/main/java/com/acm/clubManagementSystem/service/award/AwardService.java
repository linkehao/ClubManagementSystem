package com.acm.clubManagementSystem.service.award;

import com.acm.clubManagementSystem.entity.award.Award;
import com.acm.clubManagementSystem.entity.student.Student;
import com.acm.clubManagementSystem.mapper.award.AwardMapper;
import com.acm.clubManagementSystem.mapper.student.StudentMapper;
import com.acm.clubManagementSystem.vo.AwardScreenReqVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AwardService {
    @Autowired
    private AwardMapper mapper;

    @Autowired
    private StudentMapper studentMapper;

    public PageInfo<Award> selectScreen(AwardScreenReqVo vo) {
        PageHelper.startPage(vo.getPage(),vo.getLimit(),true);
//        根据查询条件
        Map<String, Object> param =getMap(vo);
        List<Award> awards=mapper.selectAllByCondition(param);
        for (Award award : awards) {
            Student mentor = studentMapper.selectByMId(award.getMentorId());
            award.setMentorName(mentor.getName());
        }
        return PageInfo.of(awards);
    }

    public Map<String, Object> getMap(AwardScreenReqVo vo){
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
        award.setFlag(0);
        mapper.insert(award);
    }

    /**
     * 团队信息编辑
     * @param award
     */
    @Transactional
    public void update(Award award) {
        award.setFlag(0);
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
