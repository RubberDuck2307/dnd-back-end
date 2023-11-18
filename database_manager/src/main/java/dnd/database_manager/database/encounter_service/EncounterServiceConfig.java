package dnd.database_manager.database.encounter_service;

public class EncounterServiceConfig {

    /**
     * Names of the tables and schemas used by the encounter service
     */
    public static final String SCHEMA = "encounter_service";
    public static final String MONSTER_VIEW_TABLE = "monster_view";
    public static final String MONSTER_VIEW_GROUP_MONSTER_TABLE = "monster_monster_group";

    /**
     * Paths to the DDL scripts used to create the encounter service
     */
    public static final String MONSTER_VIEW_DDL_SCRIPT = "encounter_service/monster_view_ddl.sql";

}
