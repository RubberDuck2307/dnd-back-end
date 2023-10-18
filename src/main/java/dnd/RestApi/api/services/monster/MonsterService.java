package dnd.RestApi.api.services.monster;

import dnd.RestApi.api.repositories.monster.MonsterRepository;
import dnd.RestApi.game.creature.monster.Monster;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MonsterService {

    private final MonsterRepository monsterRepository;

    public Monster getMonsterById(Long id){
        return monsterRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Monster getMonsterByName(String name){
        return monsterRepository.findByMonsterNameIgnoreCase(name).orElseThrow(NoSuchElementException::new);
    }

}
