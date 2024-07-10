package com.example.swscreen.service;

import com.example.swscreen.domain.News;

import java.util.List;

public interface NewsService {

    News createNews(News middleInfo);

    List<News> getAllNews();
}
