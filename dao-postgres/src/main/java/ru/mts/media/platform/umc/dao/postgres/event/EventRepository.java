package ru.mts.media.platform.umc.dao.postgres.event;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface EventRepository extends JpaRepository<EventEntity, Long> {

    @Query("SELECT e FROM EventEntity e inner join e.venues v  where v.referenceId = :referenceId")
    List<EventEntity> findByVenueReferenceId(@Param("referenceId") String referenceId);

}
