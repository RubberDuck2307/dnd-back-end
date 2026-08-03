package dnd.adventure_service.service;

import dnd.adventure_service.persistence.entity.LinkEntity;
import dnd.adventure_service.persistence.repo.GenericEntityRepo;
import dnd.adventure_service.persistence.repo.LinkRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LinkService {

    private final LinkRepo linkRepo;
    private final GenericEntityRepo genericEntityRepo;

    public LinkEntity createLink(LinkEntity linkEntity) {
        linkEntity.setSource(genericEntityRepo.getReferenceById(linkEntity.getId().getSource()));
        linkEntity.setTarget(genericEntityRepo.getReferenceById(linkEntity.getId().getTarget()));
        return linkRepo.save(linkEntity);
    }
}