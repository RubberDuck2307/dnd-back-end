package dnd.dto;

public class ConditionDTO {

    private String conditionName;
    private String description;
    public ConditionDTO(String conditionName, String description) {
        this.conditionName = conditionName;
        this.description = description;
    }
    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
