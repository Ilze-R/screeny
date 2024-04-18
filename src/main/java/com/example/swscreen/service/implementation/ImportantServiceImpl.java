package com.example.swscreen.service.implementation;

import com.example.swscreen.domain.BelowInfo;
import com.example.swscreen.repository.ImportantRepository;
import com.example.swscreen.service.ImportantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImportantServiceImpl implements ImportantService {

    private final ImportantRepository<BelowInfo> importantRepository;

    @Override
    public BelowInfo createImportant(BelowInfo belowInfo) {
        return importantRepository.createImportant(belowInfo);
    }

    @Override
    public void deleteImportant(Long id) {
        importantRepository.deleteImportant(id);
    }

    @Override
    public List<BelowInfo> getAllImportant() {
        return importantRepository.getAllImportant();
    }
}
