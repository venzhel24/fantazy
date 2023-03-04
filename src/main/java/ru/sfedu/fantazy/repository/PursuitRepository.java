package ru.sfedu.fantazy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.sfedu.fantazy.model.Pursuit;

public interface PursuitRepository extends JpaRepository<Pursuit, Long> {
}
