package dnd.monster_service.http.controller;

import dnd.monster_service.http.dto.cr.CrRangeDto;
import dnd.monster_service.http.dto.monster.CountMonstersDTO;
import dnd.monster_service.http.dto.monster.GetFullMonsterDTO;
import dnd.monster_service.http.dto.monster.MonsterGetShortDTO;
import dnd.monster_service.http.dto.mapper.DtoMapper;
import dnd.monster_service.model.cr.CrRange;
import dnd.monster_service.persistance.entity.creature.monster.Monster;
import dnd.monster_service.persistance.repository.monster.MonsterSearchFilter;
import dnd.monster_service.persistance.repository.monster.MonsterSearchSorting;
import dnd.monster_service.service.MonsterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class MonsterController {

    private final MonsterService monsterService;
    private final DtoMapper dtoMapper;

    @GetMapping("/{id}")
    public ResponseEntity<GetFullMonsterDTO> getMonsterById(@PathVariable Long id) {
        Monster monster = monsterService.getMonsterById(id);
        GetFullMonsterDTO monsterDTO = dtoMapper.buildGetFullMonsterDTO(monster);
        return ResponseEntity.ok(monsterDTO);
    }

    @GetMapping("/count")
    public ResponseEntity<CountMonstersDTO> countMonsters(@RequestParam(required = false) String name,
                                                          @RequestParam(required = false) String type,
                                                          @RequestParam(required = false) Float minCr,
                                                          @RequestParam(required = false) Float maxCr) {
        long count = monsterService.getAmountOfMonstersFiltered(
                new MonsterSearchFilter(name, type, minCr, maxCr, null));
        return ResponseEntity.ok(dtoMapper.buildCountMonstersDTO(count));
    }

    @GetMapping("/")
    public ResponseEntity<List<MonsterGetShortDTO>> getMonsters
            (@RequestParam(defaultValue = "0") int page,
             @RequestParam(defaultValue = "20") int amount,
             @RequestParam(required = false) String name,
             @RequestParam(required = false) String type,
             @RequestParam(required = false) Float minCr,
             @RequestParam(required = false) Float maxCr,
             @RequestParam(required = false) Long groupId,
             @RequestParam(required = false, defaultValue = "true") Boolean ascending,
             @RequestParam(required = false, defaultValue = "name") String order) {
        MonsterSearchFilter filter = new MonsterSearchFilter(name, type, minCr, maxCr, groupId);
        MonsterSearchSorting sorting = new MonsterSearchSorting(ascending, order.toUpperCase());
        List<MonsterGetShortDTO> monsters = monsterService.getMonsters(amount, page, filter, sorting)
                .stream()
                .map(dtoMapper::buildMonsterGetShortDTO)
                .toList();
        return ResponseEntity.ok(monsters);
    }

    @GetMapping("cr/range")
    public ResponseEntity<CrRangeDto> getCrRange(){
        CrRange range = monsterService.getCrRange();
        CrRangeDto dto = new CrRangeDto(range.getMinCr(), range.getMaxCr());
       return ResponseEntity.ok(dto);
    }

}
