package dnd.RestApi.game.creature.monster;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class MonsterService {
    private final MonsterRepository monsterRepository;

    public void saveMultiple(Collection<Monster> monsters) {
        monsterRepository.saveAll(monsters);
    }


}
