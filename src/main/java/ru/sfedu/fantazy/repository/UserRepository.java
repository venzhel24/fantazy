package ru.sfedu.fantazy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sfedu.fantazy.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByEmail(String email);
}
