package com.example.swscreen.service.implementation;

import com.example.swscreen.domain.News;
import com.example.swscreen.repository.NewsRepository;
import com.example.swscreen.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository<News> newsRepository;
    @Override
    public News createNews(News news) {
        return newsRepository.createNews(news);
    }

    @Override
    public List<News> getAllNews() {
        return newsRepository.getAllNews();
    }
}
