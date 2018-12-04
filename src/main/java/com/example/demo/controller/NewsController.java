package com.example.demo.controller;

import com.example.demo.domain.News;
import com.example.demo.entity.NewsVo;
import com.example.demo.service.NewsService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {
    @Resource
    private NewsService newsService;

    @RequestMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void toIndex(HttpServletRequest request, @RequestBody NewsVo newsVo){
        News n = new News();
        n.setTitle(newsVo.getTitle());
        n.setContent(newsVo.getContent().getBytes());
        n.setCreateTime(new Date().getTime());
        n.setModifyTime(new Date().getTime());
        newsService.addNews(n);
    }

    @RequestMapping("/all")
    @ResponseBody
    public List<News> toIndex(HttpServletRequest request){
        return newsService.getNews();
    }

    @RequestMapping("/getNews")
    @ResponseBody
    public NewsVo getNewsById(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        News n = this.newsService.getNewsById(id);
        NewsVo nVo = new NewsVo();
        BeanUtils.copyProperties(n, nVo);
        nVo.setContent(new String(n.getContent()));
        return nVo;
    }
}
