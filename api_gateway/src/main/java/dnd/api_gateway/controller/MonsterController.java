package dnd.api_gateway.controller;

import dnd.api_gateway.service.monster.MonsterGrpcClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/monster")
@RequiredArgsConstructor
public class MonsterController {

    private final MonsterGrpcClient monsterGrpcClient;
    @GetMapping("/")
    public String getMonsters(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size){
        monsterGrpcClient.getMonsters(page, size);
        return "Hello World";
    }

    @GetMapping("/{id}")
    public String getMonsterById(@PathVariable String id){
        return "Hello World";
    }



}
