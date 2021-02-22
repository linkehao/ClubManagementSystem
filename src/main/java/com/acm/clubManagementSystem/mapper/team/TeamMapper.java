package com.acm.clubManagementSystem.mapper.team;

import com.acm.clubManagementSystem.entity.team.Team;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TeamMapper {
    int deleteByPrimaryKey(String id);

    int insert(Team record);

    int insertSelective(Team record);

    Team selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Team record);

    int updateByPrimaryKey(Team record);


    List<Team> selectAllByCondition(Map<String, Object> param);

    Team selectBySId(String sid);

    Team selectByTeamName(String teamName);

    List<Team> selectByMentorId(String mid);
}