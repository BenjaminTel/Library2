package com.ubik.formation.library2.repository;

import com.ubik.formation.library2.model.Book;

import java.util.List;

public interface BookDao {

    Book findById(Long id);

    List<Book> findAll(int page, int size, String sort, String direction);

    void save(Book book);

    void delete(Book book);

    void deleteByIds(List<Long> ids);

    Long countBooks();
}
