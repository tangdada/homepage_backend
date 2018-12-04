package com.example.demo.service;

import com.example.demo.domain.News;

import java.util.List;

public interface NewsService {
    public boolean addNews(News news);

    public News getNewsById(int id);

    public List<News> getNews();
}
