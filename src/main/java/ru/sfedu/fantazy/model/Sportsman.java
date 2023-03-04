package ru.sfedu.fantazy.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Table(name = "sportsmen")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class Sportsman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sportsman sportsman = (Sportsman) o;
        return Objects.equals(name, sportsman.name) && Objects.equals(country, sportsman.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country);
    }
}
