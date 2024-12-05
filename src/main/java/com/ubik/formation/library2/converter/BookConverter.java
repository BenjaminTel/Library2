package com.ubik.formation.library2.converter;

import com.ubik.formation.library2.dto.BookDto;
import com.ubik.formation.library2.model.Author;
import com.ubik.formation.library2.model.Book;
import com.ubik.formation.library2.model.Tag;
import com.ubik.formation.library2.service.AuthorService;
import com.ubik.formation.library2.service.BookService;
import com.ubik.formation.library2.service.TagService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookConverter {

    private final AuthorService authorService;
    private final TagService tagService;
    private final BookService bookService;

    public BookConverter(AuthorService authorService, TagService tagService, BookService bookService) {
        this.authorService = authorService;
        this.tagService = tagService;
        this.bookService = bookService;
    }

    public BookDto convertEntityToDto(Book book) {
        BookDto bookDto = new BookDto();

        if (book == null) {
            return bookDto;
        }

        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setPublicationDate(book.getPublicationDate());

        if (book.getAuthor() != null) {
            bookDto.setAuthorId(book.getAuthor().getId());
        }

        if (book.getTags() != null) {
            List<String> tagNames = book.getTags().stream()
                    .map(Tag::getName)
                    .collect(Collectors.toList());
            bookDto.setTagNames(tagNames);
        }

        return bookDto;
    }

    public Book convertDtoToEntity(BookDto bookDto) {
        Book book = (bookDto.getId() != null)
                ? bookService.findById(bookDto.getId())
                : new Book();

        book.setTitle(bookDto.getTitle());
        book.setPublicationDate(bookDto.getPublicationDate());

        Author author = authorService.findById(bookDto.getAuthorId());
        book.setAuthor(author);

        Set<Tag> tags = new HashSet<>();
        for (String tagName : bookDto.getTagNames()) {
            if (!tagName.trim().isEmpty()) {
                Tag tag = tagService.findOrCreateByName(tagName.trim());
                tags.add(tag);
            }
        }
        book.setTags(tags);

        return book;
    }
}
