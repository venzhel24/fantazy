package ru.sfedu.fantazy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

@Entity
@Table(name = "mass_start")
@Getter
@Setter
@NoArgsConstructor
@ToString
@SuperBuilder
public class MassStart extends Result{

    private int prone_shooting1;
    private int standing_shooting1;
    private int prone_shooting2;
    private int standing_shooting2;
    private int total_misses;

    private String time;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MassStart massStart = (MassStart) o;
        return prone_shooting1 == massStart.prone_shooting1 && standing_shooting1 == massStart.standing_shooting1 && prone_shooting2 == massStart.prone_shooting2 && standing_shooting2 == massStart.standing_shooting2 && total_misses == massStart.total_misses && Objects.equals(time, massStart.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), prone_shooting1, standing_shooting1, prone_shooting2, standing_shooting2, total_misses, time);
    }
}
