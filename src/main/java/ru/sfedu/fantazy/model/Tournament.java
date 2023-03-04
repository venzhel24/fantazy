package ru.sfedu.fantazy.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Table(name = "tournaments")
@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String city;

    private int start_day;

    private int end_day;

    private String month;

    private int year;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tournament that = (Tournament) o;
        return start_day == that.start_day && end_day == that.end_day && year == that.year && Objects.equals(city, that.city) && Objects.equals(month, that.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, start_day, end_day, month, year);
    }
}
