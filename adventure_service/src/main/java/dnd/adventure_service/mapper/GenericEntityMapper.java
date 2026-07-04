package dnd.adventure_service.mapper;

import dnd.adventure_service.api.dto.CreateGenericEntityDto;
import dnd.adventure_service.persistence.entity.GenericEntity;
import dnd.adventure_service.persistence.entity.GenericEntityType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GenericEntityMapper {

    @Mapping(target = "uuid", ignore = true)
    GenericEntity toEntity(CreateGenericEntityDto dto);

    default GenericEntityType map(String type) {
        return GenericEntityType.fromValue(type);
    }
}