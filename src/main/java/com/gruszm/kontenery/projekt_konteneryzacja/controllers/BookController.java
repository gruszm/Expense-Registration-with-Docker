package com.gruszm.kontenery.projekt_konteneryzacja.controllers;

import com.gruszm.kontenery.projekt_konteneryzacja.entities.Book;
import com.gruszm.kontenery.projekt_konteneryzacja.repositories.BookRepository;
import com.gruszm.kontenery.projekt_konteneryzacja.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookController
{
    private BookRepository bookRepository;
    private BookService bookService;

    @Autowired
    public BookController(BookRepository bookRepository, BookService bookService)
    {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String showHome() {
        return "home";
    }

    @GetMapping("/books")
    public String getAllBooks(Model model)
    {
        List<Book> books = bookRepository.findAll();

        model.addAttribute("books", books);

        return "book-list";
    }

    @GetMapping("/add-book")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping("/api/books")
    public String addBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/add-book";  // Przekierowanie na formularz po dodaniu książki
    }
}
