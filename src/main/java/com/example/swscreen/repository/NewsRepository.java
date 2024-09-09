package com.example.swscreen.repository;

import com.example.swscreen.domain.FavouriteNews;
import com.example.swscreen.domain.News;

import java.util.List;

public interface NewsRepository<T extends News>{

    T createNews(News news);

    List<News> getAllNews();

    void deleteNews(Long id);

    void updateNews(Long id, String title, String description);

    void deactivateNews(Long id);

    void activateNews(Long id);

    FavouriteNews saveFavouriteNews(FavouriteNews historyNews, Long id);

    void deleteFavouriteNews(Long id);

    List<FavouriteNews> getAllFavouriteNews();

}
