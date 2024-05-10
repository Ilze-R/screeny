package com.example.swscreen.service.implementation;

import com.example.swscreen.domain.BelowInfo;
import com.example.swscreen.domain.CurrentBelowInfo;
import com.example.swscreen.domain.SavedBelowInfo;
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
    public SavedBelowInfo saveImportant(SavedBelowInfo savedBelowInfo, Long id) {
        return importantRepository.saveImportant(savedBelowInfo, id);
    }

    @Override
    public void deleteImportant(Long id) {
        importantRepository.deleteImportant(id);
    }

    @Override
    public void deleteFavouriteImportant(Long id) {
        importantRepository.deleteFavouriteImportant(id);
    }

    @Override
    public List<BelowInfo> getAllImportant() {
        return importantRepository.getAllImportant();
    }

    @Override
    public List<SavedBelowInfo> getAllSavedImportant() {
        return importantRepository.getAllSavedImportant();
    }

    @Override
    public CurrentBelowInfo getCurrentImportant() {
        return importantRepository.getCurrentImportant();
    }

    @Override
    public void updateImportantInformation(Long id, String description) {
         importantRepository.updateImportantInformation(id, description);
    }
}
