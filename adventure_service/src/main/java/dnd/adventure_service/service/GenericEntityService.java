package dnd.adventure_service.service;

import dnd.adventure_service.persistence.entity.GenericEntity;
import dnd.adventure_service.persistence.repo.GenericEntityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GenericEntityService {

    private final GenericEntityRepo genericEntityRepo;

    public GenericEntity createGenericEntity(GenericEntity genericEntity) {
        return genericEntityRepo.save(genericEntity);
    }

    public List<GenericEntity> getGenericEntities(UUID adventureId, List<String> names) {
        return genericEntityRepo.findAllByAdventure_IdAndNameIn(adventureId, names);
    }
}