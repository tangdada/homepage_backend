package com.example.demo.controller;


import com.example.demo.domain.Comment;
import com.example.demo.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private CommentService commentService;

    @RequestMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void toIndex(HttpServletRequest request, @RequestBody Comment comment){
        comment.setCreateTime(new Date().getTime());
        comment.setModifyTime(new Date().getTime());
        commentService.addComment(comment);
    }

    @RequestMapping("/all")
    @ResponseBody
    public List<Comment> toIndex(HttpServletRequest request){
        int articleId = Integer.parseInt(request.getParameter("id"));
        return commentService.getCommentByArticle(articleId);
    }

}