package com.acm.clubManagementSystem.mapper.topic;

import com.acm.clubManagementSystem.entity.topic.Topic;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TopicMapper {
    int deleteByPrimaryKey(String id);

    int insert(Topic record);

    int insertSelective(Topic record);

    Topic selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKeyWithBLOBs(Topic record);

    int updateByPrimaryKey(Topic record);

    List<Topic> selectAllByCondition(Map<String, Object> param);

    List<Topic> selectBySId(String sid);
}