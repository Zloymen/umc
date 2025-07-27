package ru.mts.media.platform.umc.dao.postgres.venue;

import jakarta.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.mts.media.platform.umc.domain.gql.types.FullExternalId;
import ru.mts.media.platform.umc.domain.gql.types.Venue;
import ru.mts.media.platform.umc.domain.venue.VenueSave;
import ru.mts.media.platform.umc.domain.venue.VenueSot;

@Component
@RequiredArgsConstructor
class VenuePgDao implements VenueSot {
    private final VenuePgRepository repository;
    private final VenuePgMapper mapper;

    @Override
    @Transactional
    public Optional<Venue> getVenueByReferenceId(String id) {
        return Optional.of(id)
                .map(repository::findByReferenceId)
                .map(mapper::asModel);
    }

    @Override
    public Optional<Venue> getVenueById(FullExternalId externalId) {
        return Optional.of(externalId)
                .map(mapper::asPk)
                .flatMap(repository::findById)
                .map(mapper::asModel);
    }

    @Override
    public List<Venue> getVenueByIds(List<String> ids) {
        return Optional.of(ids)
                .filter(CollectionUtils::isNotEmpty)
                .map(repository::findAllByReferenceIdIn)
                .stream().flatMap(Collection::stream)
                .map(mapper::asModel).toList();
    }

    @Override
    public List<Venue> getVenuesWithLastEventByIds(List<String> ids) {
        return Optional.of(ids)
                .filter(CollectionUtils::isNotEmpty)
                .map(repository::findAllByReferenceIdIn)
                .stream().flatMap(Collection::stream)
                .map(mapper::asModel).toList();
    }

    @EventListener
    public void handleVenueCreatedEvent(VenueSave evt) {
        evt.unwrap()
                .map(mapper::asEntity)
                .ifPresent(repository::save);
    }
}
