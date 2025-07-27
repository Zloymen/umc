package ru.mts.media.platform.umc.api.gql.event;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import ru.mts.media.platform.umc.domain.event.EventSot;
import ru.mts.media.platform.umc.domain.gql.types.Event;

@DgsComponent
@RequiredArgsConstructor
public class EventDgsQuery {
    private final EventSot sot;

    @DgsQuery
    public List<Event> eventByVenueReferenceId(@InputArgument String id) {
        return Optional.of(id).map(sot::findByVenueReferenceId).orElse(List.of());
    }

}