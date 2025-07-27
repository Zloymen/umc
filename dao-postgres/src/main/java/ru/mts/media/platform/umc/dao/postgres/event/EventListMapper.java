package ru.mts.media.platform.umc.dao.postgres.event;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import java.util.List;
import org.mapstruct.Mapper;
import ru.mts.media.platform.umc.domain.gql.types.Event;

@Mapper(componentModel = SPRING, uses = {EventPgMapper.class})
public interface EventListMapper {
    List<EventEntity> asModelList(List<Event> dloList);
    List<Event> asEntityList(List<EventEntity> modelList);
}
