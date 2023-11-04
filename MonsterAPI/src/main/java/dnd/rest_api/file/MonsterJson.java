package dnd.rest_api.file;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * This class is used to read the json file containing the monsters.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MonsterJson {
    @JsonProperty("name")
    private String name;
    @JsonProperty("meta")
    private String meta;
    @JsonProperty("Armor Class")
    private String armorClass;
    @JsonProperty("Hit Points")
    private String hitPoints;
    @JsonProperty("Speed")
    private String speed;
    @JsonProperty("STR")
    private int STR;
    @JsonProperty("DEX")
    private int DEX;
    @JsonProperty("CON")
    private int CON;
    @JsonProperty("INT")
    private int INT;
    @JsonProperty("WIS")
    private int WIS;
    @JsonProperty("CHA")
    private int CHA;
    @JsonProperty("Saving Throws")
    private String savingThrows;
    @JsonProperty("Skills")
    private String skills;
    @JsonProperty("Senses")
    private String senses;
    @JsonProperty("Languages")
    private String languages;
    @JsonProperty("Challenge")
    private String challenge;
    @JsonProperty("Traits")
    private String traits;
    @JsonProperty("Actions")
    private String actions;
    @JsonProperty("Legendary Actions")
    private String legendaryActions;
    @JsonProperty("Reactions")
    private String reactions;
    @JsonProperty ("img_url")
    private String imgUrl;
    @JsonProperty("Damage Immunities")
    private String damageImmunities;
    @JsonProperty("Damage Resistances")
    private String damageResistances;
    @JsonProperty("Condition Immunities")
    private String conditionImmunities;
    @JsonProperty("Damage Vulnerabilities")
    private String damageVulnerabilities;
}
