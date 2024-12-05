package com.ubik.formation.library2.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ubik.formation.library2.model.Tag;

import java.util.List;
import java.util.Optional;

@Repository
public class TagDaoImpl implements TagDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Tag findById(long id) {
        return entityManager.find(Tag.class, id);
    }

    @Override
    public Optional<Tag> findByName(String name) {
        try {
            return Optional.of(
                    entityManager.createQuery("SELECT t FROM Tag t WHERE t.name = :name", Tag.class)
                            .setParameter("name", name)
                            .getSingleResult()
            );
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Tag> findAll() {
        return entityManager.createQuery("SELECT t FROM Tag t", Tag.class).getResultList();
    }

    @Override
    public void save(Tag tag) {
        if (tag.getId() == null) {
            entityManager.persist(tag);
        } else {
            entityManager.merge(tag);
        }
    }

    @Override
    public void delete(Tag tag) {
        if (entityManager.contains(tag)) {
            entityManager.remove(tag);
        } else {
            entityManager.remove(entityManager.merge(tag));
        }
    }
}
