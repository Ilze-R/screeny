package com.example.swscreen.service.implementation;

import com.example.swscreen.domain.FavouriteImportant;
import com.example.swscreen.domain.Important;
import com.example.swscreen.repository.ImportantRepository;
import com.example.swscreen.service.ImportantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImportantServiceImpl implements ImportantService {

    private final ImportantRepository<Important> importantRepository;

    @Override
    public Important createImportant(Important belowInfo) {
        return importantRepository.createImportant(belowInfo);
    }

    @Override
    public FavouriteImportant saveFavouriteImportant(FavouriteImportant favouriteImportant, Long id) {
        return importantRepository.saveFavouriteImportant(favouriteImportant, id);
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
    public List<Important> getAllImportant() {
        return importantRepository.getAllImportant();
    }

    @Override
    public List<FavouriteImportant> getAllFavouriteImportant() {
        return importantRepository.getAllFavouriteImportant();
    }

    @Override
    public void updateImportant(Long id, String description) {
         importantRepository.updateImportant(id, description);
    }

    @Override
    public void deactivateImportant(Long id) {
        importantRepository.deactivateImportant(id);
    }

    @Override
    public void activateImportant(Long id) {
        importantRepository.activateImportant(id);
    }
}
