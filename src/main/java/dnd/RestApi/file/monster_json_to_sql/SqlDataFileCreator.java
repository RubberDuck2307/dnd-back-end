package dnd.RestApi.file.monster_json_to_sql;

import dnd.RestApi.config.SQLConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class SqlDataFileCreator {
    public static final String MONSTERS_FILE_PATH = "src/main/resources/data.sql";
    private final FileWriter fileWriter;

    private final Map<String, Long> sizesIdMap;
    private final Map<String, Long> typesIdMap;
    private final Map<String, Long> sensesIdMap;
    private final Map<String, Long> languagesIdMap;
    public SqlDataFileCreator() throws IOException {
            this.fileWriter = new FileWriter(new File(MONSTERS_FILE_PATH), true);
            sizesIdMap = new HashMap<>();
            typesIdMap = new HashMap<>();
            sensesIdMap = new HashMap<>();
            languagesIdMap = new HashMap<>();
    }

    public void writeCreatureSizes(Set<String> sizes) {
        StringBuilder stringBuilder = new StringBuilder();
        AtomicInteger index = new AtomicInteger(1);
        sizes.forEach(size ->{ stringBuilder.append("INSERT INTO ").append(SQLConfig.schema)
                .append(".")
                .append(SQLConfig.creature_size_table)
                .append(" (id, size) VALUES (")
                .append(index.get())
                .append(", '")
                .append(size)
                .append("');\n");
                sizesIdMap.put(size, (long) index.get());
                index.getAndIncrement();
        });
        stringBuilder.append("\n");
        writeIntoFile(stringBuilder);
    }

    public void writeMonsterTypes(Set<String> types){
        StringBuilder stringBuilder = new StringBuilder();
        AtomicInteger index = new AtomicInteger(1);
        types.forEach(type ->{ stringBuilder.append("INSERT INTO ").append(SQLConfig.schema)
                .append(".")
                .append(SQLConfig.monster_type_table)
                .append(" (id, name) VALUES (")
                .append(index.get())
                .append(", '")
                .append(type)
                .append("');\n");
                typesIdMap.put(type, (long) index.get());
                index.getAndIncrement();
        });
        stringBuilder.append("\n");
        writeIntoFile(stringBuilder);
    }

    public void writeSenses(Set<String> senses){
        StringBuilder stringBuilder = new StringBuilder();
        AtomicInteger index = new AtomicInteger(1);
        senses.forEach(sense ->{ stringBuilder.append("INSERT INTO ").append(SQLConfig.schema)
                .append(".")
                .append(SQLConfig.sense_table)
                .append(" (id, name) VALUES (")
                .append(index.get())
                .append(", '")
                .append(sense)
                .append("');\n");
                sensesIdMap.put(sense, (long) index.get());
                index.getAndIncrement();
        });
        stringBuilder.append("\n");
        writeIntoFile(stringBuilder);
    }

    public void writeLanguages(Set<String> languages){
        StringBuilder stringBuilder = new StringBuilder();
        AtomicInteger index = new AtomicInteger(1);
        languages.forEach(language ->{ stringBuilder.append("INSERT INTO ").append(SQLConfig.schema)
                .append(".")
                .append(SQLConfig.language_table)
                .append(" (id, name) VALUES (")
                .append(index.get())
                .append(", '")
                .append(language)
                .append("');\n");
                languagesIdMap.put(language, (long) index.get());
                index.getAndIncrement();
        });

        stringBuilder.append("\n");
        writeIntoFile(stringBuilder);
    }

    private void writeIntoFile(StringBuilder stringBuilder) {
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.write(stringBuilder.toString());
        printWriter.close();
    }
}
