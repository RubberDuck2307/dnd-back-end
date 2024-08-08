package dnd.monster_service.http.controller;

import dnd.monster_service.http.dto.monster_group.CreateMonsterGroupDTO;
import dnd.monster_service.service.MonsterGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("monster/group")
@RequiredArgsConstructor
public class MonsterGroupController {
    private  final MonsterGroupService service;
    @PostMapping("/")
    public ResponseEntity createMonsterGroup(@RequestBody CreateMonsterGroupDTO dto){
        service.createMonsterGroup(dto.getMonsterIds(), dto.getMonsterGroupName());
        return ResponseEntity.ok(null);
    }


}
