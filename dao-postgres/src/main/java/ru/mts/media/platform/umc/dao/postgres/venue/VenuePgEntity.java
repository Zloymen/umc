package ru.mts.media.platform.umc.dao.postgres.venue;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;
import lombok.ToString;
import ru.mts.media.platform.umc.dao.postgres.common.FullExternalIdPk;
import ru.mts.media.platform.umc.dao.postgres.event.EventEntity;

@Entity
@Data
@IdClass(FullExternalIdPk.class)
@Table(name = "venue",
        indexes = {
                @Index(name = "idx_venue_referenceId",
                        columnList = "referenceId",
                        unique = true)
        })
@ToString(exclude = "events")
public class VenuePgEntity {
    @Id
    private String brand;

    @Id
    private String provider;

    @Id
    private String externalId;

    private String referenceId;

    private String name;

    @ManyToMany(mappedBy = "venues")
    private List<EventEntity> events;
}
