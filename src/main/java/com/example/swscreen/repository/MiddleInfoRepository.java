package com.example.swscreen.repository;

import com.example.swscreen.domain.MiddleInfo;

import java.util.List;

public interface MiddleInfoRepository<T extends MiddleInfo>{

    T createMainInfo(MiddleInfo middleInfo);

    List<MiddleInfo> getAllMainInfo();
}
