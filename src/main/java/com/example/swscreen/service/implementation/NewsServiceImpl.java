package com.example.swscreen.service.implementation;

import com.example.swscreen.domain.FavouriteNews;
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

    @Override
    public void deleteNews(Long id) {
        newsRepository.deleteNews(id);
    }

    @Override
    public void updateNews(Long id, String title, String description) {
        newsRepository.updateNews(id, title, description);
    }

    @Override
    public void deactivateNews(Long id) {
        newsRepository.deactivateNews(id);
    }

    @Override
    public void activateNews(Long id) {
        newsRepository.activateNews(id);
    }

    @Override
    public FavouriteNews saveFavouriteNews(FavouriteNews favouriteNews, Long id) {
        return newsRepository.saveFavouriteNews(favouriteNews, id);
    }

    @Override
    public void deleteFavouriteNews(Long id) {
newsRepository.deleteFavouriteNews(id);
    }

    @Override
    public List<FavouriteNews> getAllFavouriteNews() {
        return newsRepository.getAllFavouriteNews();
    }

}
