package ru.mts.media.platform.umc.domain.event;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.SaveEventInput;

@Service
@RequiredArgsConstructor
public class EventDomainService {

    private final EventDomainServiceMapper mapper;
    private final ApplicationEventPublisher eventPublisher;

    public EventSave save(SaveEventInput input) {

        EventSave eventSave = new EventSave(mapper.patch(new Event(), input),
                                            List.of(input.getVenueReferenceId()));
        eventPublisher.publishEvent(eventSave);

        return eventSave;
    }
}
