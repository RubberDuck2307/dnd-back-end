package dnd.adventure_service.api.dto;

import java.util.UUID;

public record GenericEntityDto(UUID uuid, String name, String description, String type) {
}