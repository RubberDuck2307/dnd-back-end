package dnd.adventure_service.api.controller;

import dnd.adventure_service.api.dto.CreateAdventureDto;
import dnd.adventure_service.api.dto.GetAdventureDto;
import dnd.adventure_service.mapper.AdventureMapper;
import dnd.adventure_service.service.AdventureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adventure")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AdventureController {

    private final AdventureService adventureService;
    private final AdventureMapper adventureMapper;

    @PostMapping()
    public ResponseEntity<GetAdventureDto> createAdventure(@RequestBody CreateAdventureDto createAdventureDto) {
        var adventureEntity = adventureMapper.toEntity(createAdventureDto);
        var createdAdventureDto = adventureMapper.toGetDto(adventureService.createAdventure(adventureEntity));
        return ResponseEntity.ok(createdAdventureDto);
    }

    @GetMapping()
    public ResponseEntity<List<GetAdventureDto>> getAllAdventures() {
        var adventureEntities = adventureService.getAllAdventures();
        var adventureDtos = adventureEntities.stream()
                .map(adventureMapper::toGetDto)
                .toList();
        return ResponseEntity.ok(adventureDtos);
    }
}
