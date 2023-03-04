package ru.sfedu.fantazy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sfedu.fantazy.model.Race;

public interface RaceRepository extends JpaRepository<Race, Long> {
}
