package com.ubik.formation.library2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.ubik.formation.library2.model.Author;
import com.ubik.formation.library2.service.AuthorService;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String getAllAuthors(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            Model model
    ) {
        List<Author> authors = authorService.findAll(page, size);
        long numberOfAuthors = authorService.countAuthors();
        int totalPages = (int) Math.ceil((double) numberOfAuthors / size);
        if (page > totalPages) {
            page = totalPages;
        }
        model.addAttribute("authors", authors);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", totalPages);
        return "author/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("author", new Author());
        return "author/form";
    }

    @GetMapping("/{id}")
    public String editAuthor(@PathVariable(name = "id") Long id, Model model) {
        Author author = authorService.findById(id);
        if (author == null) {
            return "redirect:/authors";
        }

        model.addAttribute("author", author);
        return "author/form";
    }

    @PostMapping("/save")
    public String saveAuthor(@ModelAttribute("author") Author author, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                return "author/form";
            }
            authorService.save(author);
            return "redirect:/authors";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Save author error : " + e.getMessage());
            return "error/globalError";
        }
    }

    @PostMapping("/delete")
    public String deleteAuthors(@RequestParam(name = "authorIds", required = false) List<Long> authorIds, Model model) {
        if (authorIds == null || authorIds.isEmpty()) {
            model.addAttribute("errorMessage", "You must select at least one author to delete");
            return "error/globalError";
        }
        authorService.deleteAuthorsAndBooks(authorIds);
        return "redirect:/authors";
    }

}
