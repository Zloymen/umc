package ru.mts.media.platform.umc.api.gql.venue;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import ru.mts.media.platform.umc.domain.gql.types.Venue;
import ru.mts.media.platform.umc.domain.venue.VenueSot;

@DgsComponent
@RequiredArgsConstructor
public class VenueDgsQuery {
    private final VenueSot sot;

    @DgsQuery
    public Venue venueByReferenceId(@InputArgument String id) {
        return Optional.of(id).flatMap(sot::getVenueByReferenceId).orElse(null);
    }

    @DgsQuery
    public List<Venue> venuesByReferenceIds(@InputArgument List<String> ids) {

        return Optional.of(ids).map(sot::getVenueByIds).orElse(List.of());
    }

    @DgsQuery
    public List<Venue> venuesWithLastEventByReferenceIds(@InputArgument List<String> ids) {

        return Optional.of(ids).map(sot::getVenuesWithLastEventByIds).orElse(List.of());
    }
}
