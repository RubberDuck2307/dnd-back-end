package dnd.rest_api.api.controller;

import dnd.rest_api.api.dto.monster.MonsterFullGetDTO;
import dnd.rest_api.persistance.service.interfaces.MonsterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${url.versioning}/monster")
@RequiredArgsConstructor
public class MonsterController {

    private final MonsterService monsterService;

    @GetMapping("/name/{name}")
    public ResponseEntity<MonsterFullGetDTO> getMonster(@PathVariable String name) {
        return new ResponseEntity<>(monsterService.getMonsterByName(name), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<MonsterFullGetDTO> getMonster(@PathVariable Long id) {
        return new ResponseEntity<>(monsterService.getMonsterById(id), HttpStatus.OK);
    }



}
