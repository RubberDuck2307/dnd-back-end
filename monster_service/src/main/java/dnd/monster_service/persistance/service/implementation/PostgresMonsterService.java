package dnd.monster_service.persistance.service.implementation;

import dnd.monster_service.api.dto.monster.MonsterDTOMapper;
import dnd.monster_service.api.dto.monster.MonsterFullGetDTO;
import dnd.monster_service.persistance.repository.monster.MonsterRepository;
import dnd.monster_service.persistance.service.interfaces.MonsterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PostgresMonsterService implements MonsterService {

    private final MonsterRepository monsterRepository;
    private final MonsterDTOMapper monsterDTOMapper;
    @Override
    public MonsterFullGetDTO getMonsterById(Long id){
        return monsterDTOMapper.getMonsterFullGetDTO(
                monsterRepository.findById(id).orElseThrow(NoSuchElementException::new));
    }

    @Override
    public MonsterFullGetDTO getMonsterByName(String name){
        return monsterDTOMapper.getMonsterFullGetDTO(
                monsterRepository.findByMonsterNameIgnoreCase(name).orElseThrow(NoSuchElementException::new));
    }


}
