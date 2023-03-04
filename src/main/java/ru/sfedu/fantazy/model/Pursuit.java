package ru.sfedu.fantazy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "pursuit")
@Getter
@Setter
@NoArgsConstructor
@ToString
@SuperBuilder
public class Pursuit extends Result{

    private int prone_shooting1;
    private int standing_shooting1;
    private int prone_shooting2;
    private int standing_shooting2;
    private int total_misses;

    private String time;
}
