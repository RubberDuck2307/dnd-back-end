package dnd.adventure_service.api.controller;

import dnd.adventure_service.api.dto.CreateGenericEntityDto;
import dnd.adventure_service.mapper.GenericEntityMapper;
import dnd.adventure_service.service.GenericEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class GenericEntityController {

    private final GenericEntityService genericEntityService;
    private final GenericEntityMapper genericEntityMapper;


    @PostMapping("/")
    public ResponseEntity<Void> createGenericEntity(
            @RequestBody CreateGenericEntityDto createGenericEntityDto) {

        var genericEntity = genericEntityMapper.toEntity(createGenericEntityDto);
        genericEntityService.createGenericEntity(genericEntity);

        return ResponseEntity.ok().build();
    }
}
