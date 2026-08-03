package dnd.adventure_service.api.dto;

import java.util.UUID;

public record CreateLinkDto(
        UUID source,
        UUID target,
        boolean bidirectional,
        String relationship) {
}
