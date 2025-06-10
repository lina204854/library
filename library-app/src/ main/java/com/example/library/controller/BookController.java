package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public String listBooks(Model model,
                            @RequestParam(value = "keyword", required = false) String keyword,
                            @RequestParam(value = "sort", defaultValue = "id") String sortField) {
        List<Book> books;
        if (keyword != null && !keyword.trim().isEmpty()) {
            books = bookRepository.findByTitleContainingIgnoreCaseOrPublisherContainingIgnoreCaseOrStudentNameContainingIgnoreCase(keyword, keyword, keyword);
            model.addAttribute("keyword", keyword);
        } else {
            String[] sortParams = sortField.split(",");
            Sort.Direction direction = sortParams.length > 1 && sortParams[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            books = bookRepository.findAll(Sort.by(direction, sortParams[0]));
        }
        model.addAttribute("books", books);
        model.addAttribute("sortField", sortField);
        return "book-list";
    }

    @GetMapping("/books/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-form";
    }

    @PostMapping("/books/save")
    public String saveBook(@Valid @ModelAttribute("book") Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "book-form";
        }
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        return "book-form";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/api/books/issue-stats")
    @ResponseBody
    public List<Map<String, Object>> getIssueStatistics() {
        return bookRepository.countBooksByIssueDate().stream()
                .map(record -> Map.of("issueDate", record[0], "bookCount", record[1]))
                .collect(Collectors.toList());
    }
}
