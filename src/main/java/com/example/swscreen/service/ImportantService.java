package com.example.swscreen.service;

import com.example.swscreen.domain.BelowInfo;

import java.util.List;

public interface ImportantService {

    BelowInfo createImportant(BelowInfo belowInfo);

    void deleteImportant(Long id);

    List<BelowInfo> getAllImportant();
}
