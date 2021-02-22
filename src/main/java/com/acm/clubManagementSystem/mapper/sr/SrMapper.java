package com.acm.clubManagementSystem.mapper.sr;

import com.acm.clubManagementSystem.entity.sr.Sr;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SrMapper {
    int deleteByPrimaryKey(String id);

    int insert(Sr record);

    int insertSelective(Sr record);

    Sr selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Sr record);

    int updateByPrimaryKey(Sr record);

    Sr selectBySId(String sid);
}