package dnd.adventure_service.api.dto;

import java.util.UUID;

public record GetAdventureDto(UUID id, String name, String description, String imageUrl)  {

}