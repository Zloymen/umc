package ru.mts.media.platform.umc.dao.postgres.event;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.mts.media.platform.umc.domain.gql.types.Event;

@Mapper(componentModel = SPRING)
public interface EventPgMapper {
    @Mapping(target = "endTime", source = "endTime")
    @Mapping(target = "startTime", source = "startTime")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "id", source = "id")
    Event asModel(EventEntity eventPg);

    @Mapping(target = "endTime", source = "endTime")
    @Mapping(target = "startTime", source = "startTime")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "id", source = "id")
    EventEntity asEntity(Event event);

}
