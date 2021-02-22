package com.acm.clubManagementSystem.service.award;

import com.acm.clubManagementSystem.entity.award.Award;
import com.acm.clubManagementSystem.mapper.award.AwardMapper;
import com.acm.clubManagementSystem.vo.AwardSummaryScreenReqVo;
import com.acm.clubManagementSystem.vo.AwardTeamScreenReqVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AwardSummaryService {

    @Autowired
    private AwardMapper mapper;

    public PageInfo<Award> selectScreen(AwardSummaryScreenReqVo vo) {
        PageHelper.startPage(vo.getPage(),vo.getLimit(),true);
//        根据查询条件
        Map<String, Object> param =getMap(vo);
        List<Award> awards=mapper.selectByAwardSummary(vo);
        return PageInfo.of(awards);
    }

    public Map<String, Object> getMap(AwardSummaryScreenReqVo vo){
        Map<String, Object> param =new HashMap<>();
        if (StringUtils.isNotBlank(vo.getStartTime())) {
            param.put("startTime",vo.getStartTime());
        }
        if (StringUtils.isNotBlank(vo.getEndTime())) {
            param.put("endTime",vo.getEndTime());
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
        if (StringUtils.isNotBlank(vo.getSortBy())) {
            param.put("sortBy", vo.getSortBy());
        }
        if (StringUtils.isNotBlank(vo.getAscdesc())) {
            param.put("ascdesc", vo.getAscdesc());
        }
        return param;
    }
}
