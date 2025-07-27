package ru.mts.media.platform.umc.dao.postgres.event;

import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgEntity;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgRepository;
import ru.mts.media.platform.umc.domain.event.EventSave;
import ru.mts.media.platform.umc.domain.event.EventSot;
import ru.mts.media.platform.umc.domain.gql.types.Event;

@Service
@RequiredArgsConstructor
public class EventDao implements EventSot {
    private final EventRepository eventRepository;
    private final EventPgMapper eventPgMapper;
    private final VenuePgRepository venuePgRepository;

    @Override
    public List<Event> findByVenueReferenceId(String venueReferenceId) {

        return eventRepository.findByVenueReferenceId(venueReferenceId)
                .stream()
                .map(eventPgMapper::asModel)
                .toList();
    }

    @EventListener
    public void handleCreatedEvent(EventSave evt) {
        evt.unwrap()
                .ifPresent(event -> {
                    var eventEntity = eventPgMapper.asEntity(event);
                    Set<VenuePgEntity> allByReferenceIdIn =
                            venuePgRepository.getAllByReferenceIdIn(evt.getVenueReferenceIds());

                    eventEntity.setVenues(allByReferenceIdIn);
                    EventEntity save = eventRepository.save(eventEntity);
                    event.setId(save.getId());
                });
    }
}
