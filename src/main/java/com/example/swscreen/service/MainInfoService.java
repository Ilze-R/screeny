package com.example.swscreen.service;

import com.example.swscreen.domain.MidInfo;

import java.util.List;

public interface MainInfoService {

    MidInfo createMainInfo(MidInfo midInfo);

    List<MidInfo> getAllMainInfo();
}
