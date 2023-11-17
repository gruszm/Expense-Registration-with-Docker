package com.gruszm.kontenery.unused.services;

import com.gruszm.kontenery.unused.entities.Book;
import com.gruszm.kontenery.unused.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    // Inne metody serwisu, jeśli są potrzebne
}
