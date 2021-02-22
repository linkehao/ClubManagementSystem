package com.acm.clubManagementSystem.service.comment;

import com.acm.clubManagementSystem.entity.comment.Comment;
import com.acm.clubManagementSystem.entity.student.Student;
import com.acm.clubManagementSystem.entity.topic.Topic;
import com.acm.clubManagementSystem.mapper.comment.CommentMapper;
import com.acm.clubManagementSystem.mapper.student.StudentMapper;
import com.acm.clubManagementSystem.vo.CommentScreenReqVo;
import com.acm.clubManagementSystem.vo.TopicScreenReqVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentService {

    @Autowired
    private CommentMapper mapper;

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 评论新增
     * @param comment
     * @return
     */
    public void insert(Comment comment) {
        List<Comment> comments= mapper.selectAll();
        String str = String.format("%06d", comments.size()+1);//如果小于6位左边补0
        comment.setId(str);
        Date date = new Date();
        comment.setCommentTime(date);
        mapper.insert(comment);
    }

    public PageInfo<Comment> selectScreen(CommentScreenReqVo vo) {
        PageHelper.startPage(vo.getPage(),vo.getLimit(),true);
//        根据查询条件
        Map<String, Object> param =getMap(vo);
        List<Comment> comments=mapper.selectAllByCondition(param);
        for (Comment comment : comments) {
            Student student = studentMapper.selectByPrimaryKey(comment.getSid());
            comment.setSname(student.getName());
        }
        return PageInfo.of(comments);
    }

    public Map<String, Object> getMap(CommentScreenReqVo vo){
        Map<String, Object> param =new HashMap<>();
        if (StringUtils.isNotBlank(vo.getSortBy())) {
            param.put("sortBy", vo.getSortBy());
        }
        if (StringUtils.isNotBlank(vo.getAscdesc())) {
            param.put("ascdesc", vo.getAscdesc());
        }
        if (StringUtils.isNotBlank(vo.getTid())) {
            param.put("tid", vo.getTid());
        }
        if (StringUtils.isNotBlank(vo.getStartTime())) {
            param.put("startTime",vo.getStartTime());
        }
        if (StringUtils.isNotBlank(vo.getEndTime())) {
            param.put("endTime",vo.getEndTime());
        }
        return param;
    }
}
