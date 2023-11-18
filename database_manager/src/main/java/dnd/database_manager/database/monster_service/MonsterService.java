package dnd.database_manager.database.monster_service;

import dnd.database_manager.database.encounter_service.MonsterViewEntity;
import dnd.database_manager.database.encounter_service.MonsterViewGroupMonsterEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class MonsterService {

    private final JdbcTemplate jdbcTemplate;

    public MonsterService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<MonsterViewEntity> getAllMonstersFromDatabase() {
        return jdbcTemplate.query("SELECT id, monster_name, cr FROM "
                        + MonsterServiceConfig.SCHEMA + "." + MonsterServiceConfig.MONSTER_TABLE,
                (rs, rowNum) -> new MonsterViewEntity(rs.getString("monster_name"),
                        rs.getLong("id"), rs.getFloat("cr")));
    }

    public List<MonsterViewGroupMonsterEntity> getAllMonstersInGroupsFromDatabase() {
        return jdbcTemplate.query("SELECT monster_id, monster_group_id FROM "
                        + MonsterServiceConfig.SCHEMA + "." + MonsterServiceConfig.MONSTER_GROUP_MONSTER_TABLE,
                (rs, rowNum) -> new MonsterViewGroupMonsterEntity(rs.getLong("monster_id"),
                        rs.getLong("monster_group_id")));
    }
}
