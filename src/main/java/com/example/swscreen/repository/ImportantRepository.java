package com.example.swscreen.repository;

import com.example.swscreen.domain.BelowInfo;

import java.util.List;

public interface ImportantRepository  <T extends BelowInfo>{

    T createImportant(BelowInfo belowInfo);

    void deleteImportant(Long id);

    List<BelowInfo> getAllImportant();
}
