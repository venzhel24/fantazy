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
@Table(name = "sprint")
@Getter
@Setter
@NoArgsConstructor
@ToString
@SuperBuilder
public class Sprint extends Result{

    private int prone_shooting;
    private int standing_shooting;
    private int total_misses;

    private String result;

    private String behind;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Sprint sprint = (Sprint) o;
        return prone_shooting == sprint.prone_shooting && standing_shooting == sprint.standing_shooting && total_misses == sprint.total_misses && Objects.equals(result, sprint.result) && Objects.equals(behind, sprint.behind);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), prone_shooting, standing_shooting, total_misses, result, behind);
    }
}
