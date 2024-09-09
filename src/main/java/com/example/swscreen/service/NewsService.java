package com.example.swscreen.service;

import com.example.swscreen.domain.FavouriteNews;
import com.example.swscreen.domain.News;

import java.util.List;

public interface NewsService {

    News createNews(News middleInfo);

    List<News> getAllNews();

    void deleteNews(Long id);

    void updateNews(Long id, String title, String description);

    void deactivateNews(Long id);

    void activateNews(Long id);

   FavouriteNews saveFavouriteNews(FavouriteNews historyNews, Long id);

    void deleteFavouriteNews(Long id);

    List<FavouriteNews> getAllFavouriteNews();

}
