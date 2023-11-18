package dnd.database_manager.database.encounter_service;

import dnd.database_manager.Runner;
import dnd.database_manager.database.monster_service.MonsterService;
import dnd.database_manager.database.monster_service.MonsterServiceConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class EncounterServiceDatabaseManager {


    private final MonsterService monsterService;
    private final JdbcTemplate jdbcTemplate;

    private static final Logger logger = Logger.getLogger(Runner.class.getName());

    public void createView() throws SQLException {
        logger.info("Creating Monster view in EncounterService");
        ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(), new ClassPathResource(
                EncounterServiceConfig.MONSTER_VIEW_DDL_SCRIPT));
        logger.info("Monster view in EncounterService created");
    }

    public void fillInView() throws SQLException {
        logger.info("Filling monster view in EncounterService");
        fillInMonsterViewMonsterTable();
        fillInMonsterGroupMonsterTable();

    }

    private void fillInMonsterViewMonsterTable() throws SQLException {
        List<MonsterViewEntity> monsters = monsterService.getAllMonstersFromDatabase();
        logger.info("Fetched " + monsters.size() + " monsters from MonsterService");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO " + EncounterServiceConfig.SCHEMA + "." +
                EncounterServiceConfig.MONSTER_VIEW_TABLE + " (name, id, cr) VALUES ");
        for (int i = 0; i < monsters.size(); i++) {
            stringBuilder.append("(?, ?, ?)");
            if (i != monsters.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        PreparedStatement preparedStatement = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                stringBuilder.toString());
        for (int i = 0; i < monsters.size(); i++) {
            preparedStatement.setString(i * 3 + 1, monsters.get(i).getName());
            preparedStatement.setLong(i * 3 + 2, monsters.get(i).getId());
            preparedStatement.setFloat(i * 3 + 3, monsters.get(i).getCr());
        }
        preparedStatement.execute();
        logger.info("Monster view table in EncounterService filled");
    }
    private void fillInMonsterGroupMonsterTable() throws SQLException {
        List<MonsterViewGroupMonsterEntity> groups = monsterService.getAllMonstersInGroupsFromDatabase();
        logger.info("Fetched " + groups.size() + " records about monsters in groups from MonsterService");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO " + EncounterServiceConfig.SCHEMA + "." +
                EncounterServiceConfig.MONSTER_VIEW_GROUP_MONSTER_TABLE + " (monster_id, monster_group_id) VALUES ");
        for (int i = 0; i < groups.size(); i++) {
            stringBuilder.append("(?, ?)");
            if (i != groups.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        PreparedStatement preparedStatement = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                stringBuilder.toString());
        for (int i = 0; i < groups.size(); i++) {
            preparedStatement.setLong(i * 2 + 1, groups.get(i).getMonsterId());
            preparedStatement.setLong(i * 2 + 2, groups.get(i).getGroupId());
        }
        preparedStatement.execute();
        logger.info("monster group monster view  table in EncounterService filled");

    }

}
