package com.acm.clubManagementSystem.service.topic;

import com.acm.clubManagementSystem.entity.comment.Comment;
import com.acm.clubManagementSystem.entity.student.Student;
import com.acm.clubManagementSystem.entity.topic.Topic;
import com.acm.clubManagementSystem.exception.BusinessException;
import com.acm.clubManagementSystem.mapper.comment.CommentMapper;
import com.acm.clubManagementSystem.mapper.student.StudentMapper;
import com.acm.clubManagementSystem.mapper.topic.TopicMapper;
import com.acm.clubManagementSystem.vo.TopicScreenReqVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TopicService {
    @Autowired
    private TopicMapper mapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CommentMapper commentMapper;


    public PageInfo<Topic> selectScreen(TopicScreenReqVo vo) {
        PageHelper.startPage(vo.getPage(),vo.getLimit(),true);
//        根据查询条件
        Map<String, Object> param =getMap(vo);
        List<Topic> topics=mapper.selectAllByCondition(param);
        for (Topic topic : topics) {
            List<Comment> comments = commentMapper.selectByTId(topic.getId());
            for (Comment comment : comments) {
                Student student = studentMapper.selectByPrimaryKey(comment.getSid());
                comment.setSname(student.getName());
            }
            topic.setCommentList(comments);
        }
        return PageInfo.of(topics);
    }

    public Map<String, Object> getMap(TopicScreenReqVo vo){
        Map<String, Object> param =new HashMap<>();
        if (StringUtils.isNotBlank(vo.getSortBy())) {
            param.put("sortBy", vo.getSortBy());
        }
        if (StringUtils.isNotBlank(vo.getAscdesc())) {
            param.put("ascdesc", vo.getAscdesc());
        }
        if (StringUtils.isNotBlank(vo.getName())) {
            param.put("name", "%"+vo.getName()+"%");
        }
        if (StringUtils.isNotBlank(vo.getDifficultyLevel())) {
            param.put("difficultyLevel", vo.getDifficultyLevel());
        }
        if (StringUtils.isNotBlank(vo.getStartTime())) {
            param.put("startTime",vo.getStartTime());
        }
        if (StringUtils.isNotBlank(vo.getEndTime())) {
            param.put("endTime",vo.getEndTime());
        }
        return param;
    }


    /**
     * 团队信息新增
     * @param topic
     */
    @Transactional
    public void insert(Topic topic) {
        topic.setId(UUID.randomUUID().toString());
//       判断该学号是否存在
        Student student = studentMapper.selectByPrimaryKey(topic.getSid());
        if (student!=null){
            mapper.insert(topic);
        }else{
            throw new BusinessException(-1, "新增失败：该学号不存在  "+topic.getSid());
        }
    }

    /**
     * 团队信息编辑
     * @param topic
     */
    @Transactional
    public void update(Topic topic) {
//       判断该学号是否存在
        Student student = studentMapper.selectByPrimaryKey(topic.getSid());
        if (student!=null){
            mapper.updateByPrimaryKeyWithBLOBs(topic);
        }else{
            throw new BusinessException(-1, "修改失败：该学号不存在  "+topic.getSid());
        }

    }

    /**
     * 团队信息删除
     * @param id
     */
    @Transactional
    public void delete(String id) {
        List<Comment> comments=commentMapper.selectByTId(id);
        for (Comment comment : comments) {
            commentMapper.deleteByPrimaryKey(comment.getId());
        }
        mapper.deleteByPrimaryKey(id);
    }
}
