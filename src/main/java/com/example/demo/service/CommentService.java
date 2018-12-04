package com.example.demo.service;

import com.example.demo.domain.Comment;

import java.util.List;

public interface CommentService {

    boolean addComment(Comment record);

    List<Comment> getCommentByArticle(Integer articleId);
}
