package dnd.api_gateway.controller;

import dnd.api_gateway.dto.monster.MonsterGetShortDTO;
import dnd.api_gateway.adapter.MonsterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${url.versioning}/monster")
@RequiredArgsConstructor
public class MonsterController {

    private final MonsterService monsterService;

    @GetMapping("/")
    public ResponseEntity<List<MonsterGetShortDTO>> getMonsters
            (@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size,
             @RequestParam(required = false) String name,@RequestParam(required = false) String type,
             @RequestParam(required = false) Float cr, @RequestParam(required = false) Long groupId){

       List<MonsterGetShortDTO> monsters = monsterService.getMonsters(page, size, name, type, cr, groupId);
       return ResponseEntity.ok(monsters);
    }

    @GetMapping("/{id}")
    public String getMonsterById(@PathVariable String id){
        return "Hello World";
    }



}
