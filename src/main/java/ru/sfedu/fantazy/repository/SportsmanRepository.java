package ru.sfedu.fantazy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sfedu.fantazy.model.Sportsman;

public interface SportsmanRepository extends JpaRepository<Sportsman, Long> {
    Sportsman findSportsmanByName(String name);
}
