package com.example.demo.service.impl;

import com.example.demo.dao.CommentMapper;
import com.example.demo.domain.Comment;
import com.example.demo.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper dao;

    @Override
    public boolean addComment(Comment record) {
        int res = dao.insertSelective(record);
        return res > 0;
    }

    @Override
    public boolean updateSelective(Comment record) {
        int res = dao.updateByPrimaryKeySelective(record);
        return res > 0;
    }

    @Override
    public List<Comment> getCommentByArticle(Integer articleId) {
        return dao.selectByArticleId(articleId);
    }
}
