package dnd.RestApi.api.controllers;

import dnd.RestApi.api.dtos.monster.MonsterFullGetDTO;
import dnd.RestApi.api.services.monster.MonsterService;
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
        return new ResponseEntity<>(new MonsterFullGetDTO(monsterService.getMonsterByName(name)), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<MonsterFullGetDTO> getMonster(@PathVariable Long id) {
        return new ResponseEntity<>(new MonsterFullGetDTO(monsterService.getMonsterById(id)), HttpStatus.OK);
    }



}
