package com.ubik.formation.library2.repository;

import com.ubik.formation.library2.model.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> findByLogin(String login);
}
