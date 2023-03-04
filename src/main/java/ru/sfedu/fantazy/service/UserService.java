package ru.sfedu.fantazy.service;

import ru.sfedu.fantazy.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getByUsername(String username);
    User getByEmail(String email);

    void save(User user);
}
