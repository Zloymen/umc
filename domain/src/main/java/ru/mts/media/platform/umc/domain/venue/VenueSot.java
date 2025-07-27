package ru.mts.media.platform.umc.domain.venue;

import java.util.List;
import ru.mts.media.platform.umc.domain.gql.types.FullExternalId;
import ru.mts.media.platform.umc.domain.gql.types.Venue;

import java.util.Optional;

public interface VenueSot {
    Optional<Venue> getVenueByReferenceId(String id);

    Optional<Venue> getVenueById(FullExternalId externalId);

    List<Venue> getVenueByIds(List<String> ids);

    List<Venue> getVenuesWithLastEventByIds(List<String> ids);
}
