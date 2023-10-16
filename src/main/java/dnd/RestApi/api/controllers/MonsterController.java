package dnd.RestApi.api.controllers;

import dnd.RestApi.api.dtos.monster.MonsterFullGetDTO;
import dnd.RestApi.api.services.monster.MonsterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MonsterController {

    private final MonsterService monsterService;

    @GetMapping("/")
    public ResponseEntity<MonsterFullGetDTO> getMonsterById(@RequestParam Long id){
        return new ResponseEntity<>(new MonsterFullGetDTO(monsterService.getMonsterById(id)),
                HttpStatus.OK);
    }

}
