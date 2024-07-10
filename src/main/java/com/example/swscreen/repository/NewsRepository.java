package com.example.swscreen.repository;

import com.example.swscreen.domain.News;

import java.util.List;

public interface NewsRepository<T extends News>{

    T createNews(News news);

    List<News> getAllNews();
}
