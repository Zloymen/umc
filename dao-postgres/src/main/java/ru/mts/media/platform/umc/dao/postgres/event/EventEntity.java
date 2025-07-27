package ru.mts.media.platform.umc.dao.postgres.event;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Data;
import lombok.ToString;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgEntity;

@Entity
@Data
@Table(name = "event")
@ToString(exclude = {"venues"})
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "event_venue",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = {@JoinColumn(name = "venue_brand", referencedColumnName = "brand"),
                                  @JoinColumn(name = "venue_provider", referencedColumnName = "provider"),
                                  @JoinColumn(name = "venue_external_id", referencedColumnName = "externalId")})
    private Set<VenuePgEntity> venues;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

}
