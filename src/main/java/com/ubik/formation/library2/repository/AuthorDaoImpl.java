package com.ubik.formation.library2.repository;

import com.ubik.formation.library2.model.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Author findById(Long id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    public List<Author> findAll(int page, int size) {
        return entityManager.createQuery("SELECT a FROM Author a", Author.class)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public List<Author> findAll() {
        return entityManager.createQuery("SELECT a FROM Author a", Author.class)
                .getResultList();
    }

    @Override
    public void save(Author author) {
        entityManager.merge(author);
    }

    @Override
    public void delete(Author author) {
        if (entityManager.contains(author)) {
            entityManager.remove(author);
        } else {
            entityManager.remove(entityManager.merge(author));
        }
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        entityManager.createQuery("DELETE FROM Author a WHERE a.id IN :ids")
                .setParameter("ids", ids)
                .executeUpdate();
    }

    @Override
    public Long countAuthors() {
        return (Long) entityManager.createQuery("SELECT COUNT(a) FROM Author a").getSingleResult();
    }
}
