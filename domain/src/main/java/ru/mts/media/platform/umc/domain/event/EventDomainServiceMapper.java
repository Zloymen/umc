package ru.mts.media.platform.umc.domain.event;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.SaveEventInput;

@Mapper(componentModel = SPRING)
interface EventDomainServiceMapper {
    Event patch(@MappingTarget Event src, SaveEventInput updates);

}
