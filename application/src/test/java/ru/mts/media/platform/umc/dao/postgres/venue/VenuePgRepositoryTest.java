package ru.mts.media.platform.umc.dao.postgres.venue;

import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mts.media.platform.umc.JupiterTest;

@Disabled("for debug")
@Slf4j
class VenuePgRepositoryTest extends JupiterTest {

    @Autowired
    VenuePgRepository venuePgRepository;

    @Test
    void findVenueLastEventByVenuesReferenceId() {
        Set<String> venues = Set.of("venue_1", "venue_2", "venue_3", "venue_4", "venue_5", "venue_6");
        List<VenuePgEntity> venueLastEventByVenuesReferenceId = venuePgRepository
                .findVenueLastEventByVenuesReferenceId(venues);
        Assertions.assertThat(venueLastEventByVenuesReferenceId).isNotNull();
    }

}