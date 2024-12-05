package com.ubik.formation.library2.repository;

import com.ubik.formation.library2.model.Author;

import java.util.List;

public interface AuthorDao {

    Author findById(Long id);

    List<Author> findAll(int page, int size);

    List<Author> findAll();

    void save(Author author);

    void delete(Author author);

    void deleteByIds(List<Long> ids);

    Long countAuthors();
}