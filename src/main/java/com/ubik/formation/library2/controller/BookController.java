package com.ubik.formation.library2.controller;

import com.ubik.formation.library2.dto.BookDto;
import com.ubik.formation.library2.converter.BookConverter;
import com.ubik.formation.library2.model.Book;
import com.ubik.formation.library2.service.AuthorService;
import com.ubik.formation.library2.service.BookService;
import com.ubik.formation.library2.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookService bookService;
    private AuthorService authorService;
    private TagService tagService; // might be deleted
    private BookConverter bookConverter;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, TagService tagService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.tagService = tagService; // might be deleted
        this.bookConverter = new BookConverter(authorService, tagService, bookService);
    }

    @GetMapping
    public String getAllBooks(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "publicationDate") String sort,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
            Model model
    ) {
        try {
            List<Book> books = bookService.findAll(page, size, sort, direction);
            long numberOfBooks = bookService.countBooks();
            int totalPages = (int) Math.ceil((double) numberOfBooks / size);
            if (page > totalPages) {
                page = totalPages;
            }
            model.addAttribute("books", books);
            model.addAttribute("currentPage", page);
            model.addAttribute("size", size);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("sort", sort);
            model.addAttribute("direction", direction);

            return "book/list";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Not supported sort attribute!");
            return "error/globalError";
        }

    }

    @GetMapping("/{id}")
    public String editBook(@PathVariable(name = "id") Long id, Model model) {
        Book book = bookService.findById(id);
        if (book == null) {
            return "redirect:/books";
        }

        model.addAttribute("book", bookConverter.convertEntityToDto(book));
        model.addAttribute("authors", authorService.findAll());
        return "book/form";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("book", bookConverter.convertEntityToDto(new Book()));
        model.addAttribute("authors", authorService.findAll());
        return "book/form";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute("bookDTO") BookDto bookDto, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                return "book/form";
            }

            Book book = bookConverter.convertDtoToEntity(bookDto);
            bookService.save(book);
            return "redirect:/books";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "save book failed : " + e.getMessage());
            return "error/globalError";
        }
    }

    @PostMapping("/delete")
    public String deleteBooks(@RequestParam(name = "bookIds", required = false) List<Long> bookIds, Model model) {
        if (bookIds == null || bookIds.isEmpty()) {
            model.addAttribute("message", "You must select at least one book to delete");
            return "error/globalError";
        }
        bookService.deleteByIds(bookIds);
        return "redirect:/books";
    }

}
