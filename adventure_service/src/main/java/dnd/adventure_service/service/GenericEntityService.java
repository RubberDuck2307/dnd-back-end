package dnd.adventure_service.service;


import dnd.adventure_service.persistence.entity.GenericEntity;
import dnd.adventure_service.persistence.repo.GenericEntityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenericEntityService {

    private final GenericEntityRepo genericEntityRepo;


    public GenericEntity createGenericEntity(GenericEntity genericEntity) {
        return genericEntityRepo.save(genericEntity);
    }
}
