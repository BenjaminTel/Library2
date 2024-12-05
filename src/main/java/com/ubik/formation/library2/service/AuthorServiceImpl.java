package com.ubik.formation.library2.service;

import com.ubik.formation.library2.model.Author;
import com.ubik.formation.library2.repository.AuthorDao;

import com.ubik.formation.library2.repository.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {


    private final AuthorDao authorDao;
    private final BookDao bookDao;

    @Autowired
    public AuthorServiceImpl(AuthorDao authorDao, BookDao bookDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Author findById(Long id) {
        return authorDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> findAll(int page, int size) {
        return authorDao.findAll(page, size);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return authorDao.findAll();
    }

    @Override
    @Transactional
    public void save(Author author) {
        authorDao.save(author);
    }

    @Override
    @Transactional
    public void delete(Author author) {
        authorDao.delete(author);
    }

    @Override
    @Transactional
    public void deleteAuthorsAndBooks(List<Long> authorIds) {
        if (authorIds == null || authorIds.isEmpty()) {
            throw new IllegalArgumentException("Author IDs list cannot be null or empty.");
        }

        bookDao.deleteBookAssociationsAndBooksByAuthorIds(authorIds);
        authorDao.deleteByIds(authorIds);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countAuthors() {
        return authorDao.countAuthors();
    }
}