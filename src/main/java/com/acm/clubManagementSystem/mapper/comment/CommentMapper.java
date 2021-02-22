package com.acm.clubManagementSystem.mapper.comment;

import com.acm.clubManagementSystem.entity.comment.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> selectByTId(String tid);

    List<Comment> selectAllByCondition(Map<String, Object> param);

    List<Comment> selectAll();
}