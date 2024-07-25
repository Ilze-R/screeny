package com.example.swscreen.repository;

import com.example.swscreen.domain.FavouriteImportant;
import com.example.swscreen.domain.Important;

import java.util.List;

public interface ImportantRepository  <T extends Important>{

    T createImportant(Important important);

   FavouriteImportant saveFavouriteImportant(FavouriteImportant historyImportant, Long id);

    void deleteImportant(Long id);

    void deleteFavouriteImportant(Long id);

    List<Important> getAllImportant();

    List<FavouriteImportant> getAllFavouriteImportant();

     void updateImportant(Long id, String description);

     void deactivateImportant(Long id);

    void activateImportant(Long id);
}
