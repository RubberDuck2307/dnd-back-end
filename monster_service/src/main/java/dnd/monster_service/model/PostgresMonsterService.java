package dnd.monster_service.model;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dnd.monster_service.config.RabbitMqConfig;
import dnd.monster_service.persistance.entity.creature.monster.Monster;
import dnd.monster_service.persistance.repository.monster.MonsterRepository;
import dnd.monster_service.persistance.repository.monster.MonsterSearchFilter;
import dnd.monster_service.rabbitMq.transport_entity.Monster.MessageMqFactory;
import dnd.monster_service.rabbitMq.transport_entity.Monster.MonsterMq;
import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.util.Precision;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostgresMonsterService implements MonsterService {

    private final MessageMqFactory messageMqFactory;
    private final MonsterRepository monsterRepository;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public List<Monster> getRandomMonstersByCr(double cr, int amountOfMonsters) {
        Precision.round(cr, 4);
        int amount = monsterRepository.countAllByCrIs(cr);
        if (amount < amountOfMonsters) {
            amountOfMonsters = amount;
        }
        if (amountOfMonsters == 0) {
            return new ArrayList<>();
        }
        int random = (int) (Math.random() * (amount - amountOfMonsters));
        Page<Monster> monsters = monsterRepository.getAllByCrIs(cr, PageRequest.of(random, amountOfMonsters));
        return new ArrayList<>(monsters.getContent());
    }

    @Override
    public Map<Float, List<Monster>> getMonstersByCrAndGroup(Map<Float, Integer> amountOfCrs, long groupId) {
        return monsterRepository.getMonstersByCrAmountAndMonsterGroupId(amountOfCrs, groupId);
    }

    @Override
    public int getAmountOfMonsterInGroup(long groupId) {
        return monsterRepository.countByMonsterGroupId(groupId);
    }

    @Override
    public List<Float> getCrsByMonsterGroup(long groupId) {
        List<Float> avCrs;
        avCrs = monsterRepository.getAllCrWhereMonsterGroupsContaining_Id(groupId);
        return avCrs;
    }

    @Override
    public Map<Float, List<Monster>> getMonstersByCr(Map<Float, Integer> amountOfCrs) {
        return getMonstersByCrAndGroup(amountOfCrs, 0);
    }

    @Override
    public List<Monster> getMonsters(int pageSize, int pageNumber) {
        pageSize = Math.max(pageSize, 20);
        pageNumber = Math.max(pageNumber, 0);
        return monsterRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
    }

    @Override
    public List<Monster> getMonsters(int pageSize, int pageNumber, MonsterSearchFilter monsterSearchFilter) {
        return monsterRepository.getMonstersFiltered(pageSize, pageNumber, monsterSearchFilter);
    }

    public void addMonster() throws JsonProcessingException {
        MonsterMq monster = messageMqFactory.monsterCreatedMessage(1, "test", 2F, true);
        rabbitTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE_NAME, RabbitMqConfig.MONSTER_ROUTING_KEY,
                objectMapper.writeValueAsString(monster));
    }

}
