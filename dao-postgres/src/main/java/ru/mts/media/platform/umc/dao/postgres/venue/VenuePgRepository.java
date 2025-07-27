package ru.mts.media.platform.umc.dao.postgres.venue;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.mts.media.platform.umc.dao.postgres.common.FullExternalIdPk;

@Repository
public interface VenuePgRepository extends JpaRepository<VenuePgEntity, FullExternalIdPk> {

    VenuePgEntity findByReferenceId(String referenceId);

    Set<VenuePgEntity> getAllByReferenceIdIn(Collection<String> referenceIds);


    @EntityGraph(attributePaths = {"events"})
    List<VenuePgEntity> findAllByReferenceIdIn(List<String> referenceIds);


    @Query("""
      select e from VenuePgEntity e JOIN FETCH e.events v where (v.startTime, e.brand, e.externalId, e.provider)
        in (select max(ee.startTime), ww.brand, ww.externalId, ww.provider from EventEntity ee inner join ee.venues ww
          where ww.referenceId in (:referenceIds) group by ww.brand, ww.externalId, ww.provider)
    """)
    List<VenuePgEntity> findVenueLastEventByVenuesReferenceId(@Param("referenceIds") Set<String> referenceIds);

}
