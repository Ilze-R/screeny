package com.example.swscreen.service;

import com.example.swscreen.domain.BelowInfo;
import com.example.swscreen.domain.CurrentBelowInfo;
import com.example.swscreen.domain.SavedBelowInfo;

import java.util.List;

public interface ImportantService {

    BelowInfo createImportant(BelowInfo belowInfo);

   SavedBelowInfo saveImportant(SavedBelowInfo savedBelowInfo, Long id);

    void deleteImportant(Long id);
    void deleteFavouriteImportant(Long id);

    List<BelowInfo> getAllImportant();

    List<SavedBelowInfo> getAllSavedImportant();

    CurrentBelowInfo getCurrentImportant();

  void updateImportantInformation(Long id, String description);
}
