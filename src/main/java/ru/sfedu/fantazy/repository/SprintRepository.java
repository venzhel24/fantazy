package ru.sfedu.fantazy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sfedu.fantazy.model.Sprint;

public interface SprintRepository extends JpaRepository<Sprint, Long> {
}
