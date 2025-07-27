package ru.mts.media.platform.umc;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.transaction.Transactional;
import java.util.List;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.graphql.test.tester.GraphQlTester;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgEntity;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgRepository;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.Venue;

@AutoConfigureGraphQlTester
class ApplicationGraphQlTest extends JupiterTest {

    @Autowired
    private GraphQlTester tester;

    @Autowired
    VenuePgRepository venuePgRepository;

    @Test
    @Transactional
    void saveEvent() {

        VenuePgEntity venuePgEntity = new VenuePgEntity();
        venuePgEntity.setBrand("brand_1");
        venuePgEntity.setName("venue_14");
        venuePgEntity.setProvider("provider_1");
        venuePgEntity.setReferenceId("venue_14");
        venuePgEntity.setExternalId("externalId_1");

        venuePgRepository.save(venuePgEntity);

        String query = "mutation { saveEvent( input: { venueReferenceId: \"venue_14\" name: \"saveEvent7\" startTime:\"2025-08-26T00:29:34\" endTime: \"2025-08-27T00:29:34\"} ) { id name startTime } }";
        Event event = tester.document(query)
                .execute()
                .path("data.saveEvent")
                .entity(Event.class)
                .get();

        assertThat(event).isNotNull().extracting(Event::getId).isNotNull();
        assertThat(event).isNotNull().extracting(Event::getName).isEqualTo("saveEvent7");
    }

    @Test
    void venueByReferenceId() {

        String query = "query { venueByReferenceId(id: \"venue_1\") { id name events { id name } } }";
        Venue data = tester.document(query)
                .execute()
                .path("data.venueByReferenceId")
                .entity(Venue.class)
                .get();

        assertThat(data).isNotNull().extracting(Venue::getId).isNotNull();
        assertThat(data).isNotNull().extracting(Venue::getName).isEqualTo("Venue1");
    }

    @Test
    void eventByVenueReferenceId() {

        String query = "query { eventByVenueReferenceId(id: \"venue_3\") { id name } }";
        List<Event> data = tester.document(query)
                .execute()
                .path("data.eventByVenueReferenceId")
                .entityList(Event.class)
                .get();

        assertThat(data).isNotNull().size().isEqualTo(4);
    }


    @Test
    void venuesByReferenceIds() {

        String query = "query { venuesByReferenceIds(ids: [\"venue_1\", \"venue_2\"]) { id name events { id name startTime } } }";
        List<Venue> data = tester.document(query)
                .execute()
                .path("data.venuesByReferenceIds")
                .entityList(Venue.class)
                .get();

        assertThat(data).isNotNull().size().isEqualTo(2);
        assertThat(data).element(0).extracting(Venue::getEvents)
                .asInstanceOf(InstanceOfAssertFactories.LIST).size().isEqualTo(3);
        assertThat(data).element(1).extracting(Venue::getEvents)
                .asInstanceOf(InstanceOfAssertFactories.LIST).size().isEqualTo(4);
    }

    @Test
    void venuesWithLastEventByReferenceIds() {
        String query = "query { venuesWithLastEventByReferenceIds(ids: [\"venue_1\", \"venue_2\"]) { id name events { id name startTime } } }";
        List<Venue> data = tester.document(query)
                .execute()
                .path("data.venuesWithLastEventByReferenceIds")
                .entityList(Venue.class)
                .get();

        assertThat(data).isNotNull().size().isEqualTo(2);
        assertThat(data).element(0).extracting(Venue::getEvents)
                .asInstanceOf(InstanceOfAssertFactories.LIST).size().isEqualTo(3);
        assertThat(data).element(1).extracting(Venue::getEvents)
                .asInstanceOf(InstanceOfAssertFactories.LIST).size().isEqualTo(4);
    }
}
