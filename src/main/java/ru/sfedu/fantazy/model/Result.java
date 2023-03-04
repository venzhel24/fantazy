package ru.sfedu.fantazy.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
@ToString
@SuperBuilder
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race race;

    @ManyToOne
    @JoinColumn(name = "sportsman_id")
    private Sportsman sportsman;

    private int rank;
    private int bib;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return rank == result.rank && bib == result.bib && Objects.equals(race, result.race) && Objects.equals(sportsman, result.sportsman);
    }

    @Override
    public int hashCode() {
        return Objects.hash(race, sportsman, rank, bib);
    }
}
