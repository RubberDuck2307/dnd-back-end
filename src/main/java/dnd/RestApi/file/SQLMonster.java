package dnd.RestApi.file;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SQLMonster {
    private long id;
    private String name;
    private Set<String> types;
    private String size;
    private String armorClassDescription;
    private short armorClass;
    private short hitPoints;

    public SQLMonster() {

    }


}
