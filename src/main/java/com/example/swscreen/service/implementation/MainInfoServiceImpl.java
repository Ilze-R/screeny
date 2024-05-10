package com.example.swscreen.service.implementation;

import com.example.swscreen.domain.MidInfo;
import com.example.swscreen.repository.MainInfoRepository;
import com.example.swscreen.service.MainInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainInfoServiceImpl implements MainInfoService {

    private final MainInfoRepository<MidInfo> mainInfoRepository;
    @Override
    public MidInfo createMainInfo(MidInfo midInfo) {
        return mainInfoRepository.createMainInfo(midInfo);
    }

    @Override
    public List<MidInfo> getAllMainInfo() {
        return mainInfoRepository.getAllMainInfo();
    }
}
