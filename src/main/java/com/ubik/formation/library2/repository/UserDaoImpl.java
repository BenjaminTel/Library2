package com.ubik.formation.library2.repository;

import com.ubik.formation.library2.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<User> findByLogin(String login) {
        try {
            return Optional.of(
                    entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class)
                            .setParameter("login", login)
                            .getSingleResult()
            );
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
