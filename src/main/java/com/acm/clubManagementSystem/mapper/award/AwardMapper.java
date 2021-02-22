package com.acm.clubManagementSystem.mapper.award;

import com.acm.clubManagementSystem.entity.award.Award;
import com.acm.clubManagementSystem.vo.AwardSummaryScreenReqVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AwardMapper {
    int deleteByPrimaryKey(String id);

    int insert(Award record);

    int insertSelective(Award record);

    Award selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Award record);

    int updateByPrimaryKey(Award record);

    List<Award> selectAllByCondition(Map<String, Object> param);

    List<Award> selectAwardTeamByCondition(Map<String, Object> param);

    List<Award> selectByAwardSummary(AwardSummaryScreenReqVo vo);
}