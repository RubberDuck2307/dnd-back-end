package dnd.adventure_service.mapper;

import dnd.adventure_service.api.dto.CreateAdventureDto;
import dnd.adventure_service.api.dto.GetAdventureDto;
import dnd.adventure_service.persistence.entity.AdventureEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdventureMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "entities", ignore = true)
    AdventureEntity toEntity(CreateAdventureDto dto);

    GetAdventureDto toGetDto(AdventureEntity adventureEntity);

}
