package dnd.RestApi.file;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;
import java.util.Map;

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


}
