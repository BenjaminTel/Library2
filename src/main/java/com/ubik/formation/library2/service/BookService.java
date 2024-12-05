package com.ubik.formation.library2.service;

import com.ubik.formation.library2.model.Book;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface BookService {
    Book findById(Long id);

    List<Book> findAll(int page, int size, String sort, String direction) throws NoSuchFieldException;

    void save(Book book);

    void delete(Book book);

    void deleteByIds(List<Long> ids);

    Long countBooks();
}
