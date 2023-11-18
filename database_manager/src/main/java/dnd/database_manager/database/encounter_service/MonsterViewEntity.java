package dnd.database_manager.database.encounter_service;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class MonsterViewEntity {

    private String name;
    private Long id;
    private Float cr;

    public MonsterViewEntity(String name, Long id, Float cr) {
        setCr(cr);
        setId(id);
        setName(name);
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public void setCr(@NonNull Float cr) {
        this.cr = cr;
    }
}
