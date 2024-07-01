package com.example.swscreen.service.implementation;

import com.example.swscreen.domain.MiddleInfo;
import com.example.swscreen.repository.MiddleInfoRepository;
import com.example.swscreen.service.MiddleInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MiddleInfoServiceImpl implements MiddleInfoService {

    private final MiddleInfoRepository<MiddleInfo> mainInfoRepository;
    @Override
    public MiddleInfo createMainInfo(MiddleInfo middleInfo) {
        return mainInfoRepository.createMainInfo(middleInfo);
    }

    @Override
    public List<MiddleInfo> getAllMainInfo() {
        return mainInfoRepository.getAllMainInfo();
    }
}
