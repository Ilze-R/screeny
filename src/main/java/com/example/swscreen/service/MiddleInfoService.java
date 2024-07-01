package com.example.swscreen.service;

import com.example.swscreen.domain.MiddleInfo;

import java.util.List;

public interface MiddleInfoService {

    MiddleInfo createMainInfo(MiddleInfo middleInfo);

    List<MiddleInfo> getAllMainInfo();
}
