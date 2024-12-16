package com.ubik.formation.library2.repository;

import com.ubik.formation.library2.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Book findById(Long id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public List<Book> findAll(int page, int size, String sort, String direction) {
        String sortDirection = direction.equalsIgnoreCase("asc") ? "ASC" : "DESC";
        String request = "SELECT b FROM Book b ORDER BY b." + sort + " " + sortDirection;
        return entityManager.createQuery(request, Book.class)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public void save(Book book) {
        if (book.getId() == null) {
            entityManager.persist(book);
        } else {
            entityManager.merge(book);
        }
    }

    @Override
    public void delete(Book book) {
        if (entityManager.contains(book)) {
            entityManager.remove(book);
        } else {
            entityManager.remove(entityManager.merge(book));
        }
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("Author IDs list cannot be null or empty.");
        }
        List<Book> books = entityManager.createQuery(
                        "SELECT b FROM Book b WHERE b.id IN :ids", Book.class)
                .setParameter("ids", ids)
                .getResultList();

        for (Book book : books) {
            entityManager.remove(book);
        }
    }

    @Override
    public Long countBooks() {
        return (Long) entityManager.createQuery("SELECT COUNT(b) FROm Book b").getSingleResult();
    }
}