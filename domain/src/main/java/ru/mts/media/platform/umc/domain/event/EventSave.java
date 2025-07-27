package ru.mts.media.platform.umc.domain.event;

import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.mts.media.platform.umc.domain.common.EntityEvent;
import ru.mts.media.platform.umc.domain.gql.types.Event;

@EqualsAndHashCode(callSuper = true)
public class EventSave extends EntityEvent<Event> {

    @Getter
    private final List<String> venueReferenceIds;

    public EventSave(Event entity, List<String> venueReferenceIds) {
        super(entity);
        this.venueReferenceIds = venueReferenceIds;
    }

}
