package dnd.RestApi.game.creature;

public enum AbilityScore {

    STRENGTH("strength"),
    DEXTERITY("dexterity"),
    CONSTITUTION("constitution"),
    INTELLIGENCE("intelligence"),
    WISDOM("wisdom"),
    CHARISMA("charisma");

    private final String name;

    AbilityScore(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
