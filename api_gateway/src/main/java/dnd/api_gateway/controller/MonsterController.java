package dnd.api_gateway.controller;

import dnd.api_gateway.dto.monster.CountMonstersDTO;
import dnd.api_gateway.dto.monster.MonsterGetShortDTO;
import dnd.api_gateway.adapter.MonsterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monster")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MonsterController {

    private final MonsterService monsterService;

    @GetMapping("/")
    public ResponseEntity<List<MonsterGetShortDTO>> getMonsters
            (@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int amount,
             @RequestParam(required = false) String name,@RequestParam(required = false) String type,
             @RequestParam(required = false) Float cr, @RequestParam(required = false) Long groupId){

       List<MonsterGetShortDTO> monsters = monsterService.getMonsters(page, amount, name, type, cr, groupId);
       return ResponseEntity.ok(monsters);
    }

    @GetMapping("/{id}")
    public String getMonsterById(@PathVariable String id){
        return "Hello World";
    }

    @GetMapping("/count")
    public ResponseEntity<CountMonstersDTO> countMonsters(){
        return ResponseEntity.ok(monsterService.countMonsters());
    }

}
