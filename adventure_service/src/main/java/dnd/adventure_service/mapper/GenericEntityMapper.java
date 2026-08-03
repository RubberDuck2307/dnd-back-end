package dnd.adventure_service.mapper;

import dnd.adventure_service.api.dto.CreateGenericEntityDto;
import dnd.adventure_service.api.dto.GenericEntityDto;
import dnd.adventure_service.persistence.entity.GenericEntity;
import dnd.adventure_service.persistence.entity.GenericEntityType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenericEntityMapper {

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "adventure", ignore = true)
    @Mapping(target = "outgoingLinks", ignore = true)
    @Mapping(target = "incomingLinks", ignore = true)
    GenericEntity toEntity(CreateGenericEntityDto dto);

    GenericEntityDto toDto(GenericEntity entity);

    List<GenericEntityDto> toDtos(List<GenericEntity> entities);

    default GenericEntityType map(String type) {
        return GenericEntityType.fromValue(type);
    }
}