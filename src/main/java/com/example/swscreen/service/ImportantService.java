package com.example.swscreen.service;

import com.example.swscreen.domain.FavouriteImportant;
import com.example.swscreen.domain.Important;

import java.util.List;

public interface ImportantService {

   Important createImportant(Important important);

   FavouriteImportant saveFavouriteImportant(FavouriteImportant favouriteImportant, Long id);

    void deleteImportant(Long id);
    void deleteFavouriteImportant(Long id);

    List<Important> getAllImportant();

    List<FavouriteImportant> getAllFavouriteImportant();

  void updateImportant(Long id, String description);

  void deactivateImportant(Long id);

    void activateImportant(Long id);
}
