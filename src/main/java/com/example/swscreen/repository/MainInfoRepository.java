package com.example.swscreen.repository;

import com.example.swscreen.domain.MidInfo;

import java.util.List;

public interface MainInfoRepository  <T extends MidInfo>{

    T createMainInfo(MidInfo midInfo);

    List<MidInfo> getAllMainInfo();
}
