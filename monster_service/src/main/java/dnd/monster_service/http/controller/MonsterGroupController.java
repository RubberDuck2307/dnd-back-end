package dnd.monster_service.http.controller;

import dnd.monster_service.http.dto.mapper.MonsterGroupDtoMapper;
import dnd.monster_service.http.dto.monster_group.GetMonsterGroupDTO;
import dnd.monster_service.http.dto.monster_group.UpdateMonsterGroupDTO;
import dnd.monster_service.http.dto.monster_group.CreateMonsterGroupDTO;
import dnd.monster_service.service.MonsterGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("monster/group")
@RequiredArgsConstructor
public class MonsterGroupController {
    private  final MonsterGroupService service;
    private final MonsterGroupDtoMapper mapper;
    @PostMapping("/")
    public ResponseEntity<Void> createMonsterGroup(@RequestBody CreateMonsterGroupDTO dto){
        service.createMonsterGroup(dto.getMonsterIds(), dto.getMonsterGroupName());
        return ResponseEntity.ok(null);
    }


    @PutMapping("/")
    public ResponseEntity<Void> updateMonsterGroup(@RequestBody UpdateMonsterGroupDTO dto){
        service.updateMonsterGroup(dto.getAddedMonsters(), dto.getRemovedMonsters(), dto.getMonsterGroupId());
        return ResponseEntity.ok(null);
    }

    @GetMapping("/")
    public ResponseEntity<List<GetMonsterGroupDTO>>getMonsterGroups(){
        var monsterGroups = service.getMonsterGroups();
        var dtos = monsterGroups.stream().map(mapper::buildGetMonsterGroupDTO).toList();
        return ResponseEntity.ok(dtos);
    }


}
