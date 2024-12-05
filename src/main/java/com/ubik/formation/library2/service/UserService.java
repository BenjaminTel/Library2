package com.ubik.formation.library2.service;

import com.ubik.formation.library2.model.User;

import java.util.Optional;

public interface UserService {
    User authenticate(String login, String password);
}
