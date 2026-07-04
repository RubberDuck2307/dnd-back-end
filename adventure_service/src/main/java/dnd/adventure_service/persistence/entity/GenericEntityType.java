package dnd.adventure_service.persistence.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum GenericEntityType {
    LOCATION("Location"),
    CHARACTER("Character"),
    ITEM("Item"),
    CUSTOM("Custom");

    private final String value;

    GenericEntityType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static GenericEntityType fromValue(String value) {
        return Arrays.stream(values())
                .filter(type -> type.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Unknown type: " + value));
    }
}