package dnd.adventure_service.service;

import dnd.adventure_service.persistence.entity.AdventureEntity;
import dnd.adventure_service.persistence.repo.AdventureRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdventureService {

    private final AdventureRepo adventureRepo;


    public List<AdventureEntity> getAllAdventures() {
        return adventureRepo.findAll();
    }

    public AdventureEntity createAdventure(AdventureEntity adventureEntity) {
        return adventureRepo.save(adventureEntity);
    }


}
