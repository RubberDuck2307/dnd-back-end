package dnd.adventure_service.mapper;

import dnd.adventure_service.api.dto.CreateLinkDto;
import dnd.adventure_service.persistence.entity.LinkEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LinkMapper {

    @Mapping(target = "id.source", source = "source")
    @Mapping(target = "id.target", source = "target")
    @Mapping(target = "source", ignore = true)
    @Mapping(target = "target", ignore = true)
    LinkEntity toEntity(CreateLinkDto dto);
}