package dnd.RestApi.game.creature.type;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TypeService {

    private final TypeRepository typeRepository;

    public MonsterType getTypeById(Long id) {

        return typeRepository.findById(id).orElseThrow();
    }


    public MonsterType getTypeByName(String name) {
        return typeRepository.findByName(name).orElseThrow();
    }

    public boolean existsByName(String name) {
        return typeRepository.existsByName(name);
    }

    public MonsterType save(MonsterType type) {
        return typeRepository.save(type);
    }


}
