package com.example.demo.service.impl;

import com.example.demo.dao.NewsMapper;
import com.example.demo.domain.News;
import com.example.demo.service.NewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("newsService")
public class NewServiceImpl implements NewsService {

    @Resource
    private NewsMapper dao;

    @Override
    public boolean addNews(News news) {
        int r = dao.insertSelective(news);
        return r > 0;
    }

    @Override
    public News getNewsById(int id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public List<News> getNews() {
        return dao.selectNews();
    }
}
