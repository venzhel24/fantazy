package ru.sfedu.fantazy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

@Entity
@Table(name = "individual")
@Getter
@Setter
@NoArgsConstructor
@ToString
@SuperBuilder
public class Individual extends Result {

    private int prone_shooting1;
    private int standing_shooting1;
    private int prone_shooting2;
    private int standing_shooting2;
    private int total_misses;

    private String ski_time;

    private String result;

    private String behind;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Individual that = (Individual) o;
        return prone_shooting1 == that.prone_shooting1 && standing_shooting1 == that.standing_shooting1 && prone_shooting2 == that.prone_shooting2 && standing_shooting2 == that.standing_shooting2 && total_misses == that.total_misses && Objects.equals(ski_time, that.ski_time) && Objects.equals(result, that.result) && Objects.equals(behind, that.behind);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), prone_shooting1, standing_shooting1, prone_shooting2, standing_shooting2, total_misses, ski_time, result, behind);
    }
}
