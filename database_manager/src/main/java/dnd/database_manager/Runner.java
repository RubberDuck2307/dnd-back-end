package dnd.database_manager;

import dnd.database_manager.database.encounter_service.EncounterServiceDatabaseManager;
import lombok.RequiredArgsConstructor;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

    private final EncounterServiceDatabaseManager encounterServiceManager;
    @Override
    public void run(String... args) throws Exception {
        if (args.length < 2) {
            throw new Exception("Not enough arguments");
        }
        handleCommand(args[0], args[1]);
    }

    private void handleCommand(String service, String command) throws Exception {
        switch (service) {
            case "encounter_service" -> handleEncounterService(command);
            default -> throw new Exception("Unknown command");
        }
    }

    private void handleEncounterService(String command) throws Exception {
        switch (command) {
            case "create_view" -> encounterServiceManager.createView();
            case "fill_view" -> encounterServiceManager.fillInView();
            default -> throw new Exception("Unknown command");
        }
    }
}
