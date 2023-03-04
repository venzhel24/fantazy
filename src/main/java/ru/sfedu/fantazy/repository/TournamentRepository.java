package ru.sfedu.fantazy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sfedu.fantazy.model.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
}
