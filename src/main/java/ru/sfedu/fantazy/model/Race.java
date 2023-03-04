package ru.sfedu.fantazy.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Table(name = "races")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "race_type")
    private String raceType;

    @Column(name = "distance")
    private String distance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Race race = (Race) o;
        return Objects.equals(raceType, race.raceType) && Objects.equals(distance, race.distance) && Objects.equals(tournament, race.tournament);
    }

    @Override
    public int hashCode() {
        return Objects.hash(raceType, distance, tournament);
    }
}
