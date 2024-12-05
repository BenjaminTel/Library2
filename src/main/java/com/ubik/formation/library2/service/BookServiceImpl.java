package com.ubik.formation.library2.service;

import com.ubik.formation.library2.model.Book;
import com.ubik.formation.library2.repository.BookDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) { // Utilisation de BookDao ici
        this.bookDao = bookDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Book findById(Long id) {
        return bookDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll(int page, int size, String sort, String direction) throws NoSuchFieldException {
        Book.class.getDeclaredField(sort);
        return bookDao.findAll(page, size, sort, direction);
    }

    @Override
    @Transactional
    public void save(Book book) {
        bookDao.save(book);
    }

    @Override
    @Transactional
    public void delete(Book book) {
        bookDao.delete(book);
    }

    @Override
    @Transactional
    public void deleteByIds(List<Long> ids) {
        bookDao.deleteByIds(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countBooks() {
        return bookDao.countBooks();
    }

}
