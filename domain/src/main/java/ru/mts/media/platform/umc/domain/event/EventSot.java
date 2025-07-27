package ru.mts.media.platform.umc.domain.event;

import java.util.List;
import ru.mts.media.platform.umc.domain.gql.types.Event;

public interface EventSot {

    List<Event> findByVenueReferenceId(String venueReferenceId);
}
