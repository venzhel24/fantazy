package ru.sfedu.fantazy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sfedu.fantazy.model.Individual;

public interface IndividualRepository extends JpaRepository<Individual, Long> {
}
