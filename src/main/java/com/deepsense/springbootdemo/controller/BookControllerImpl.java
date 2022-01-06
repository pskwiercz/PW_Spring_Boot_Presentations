package com.deepsense.springbootdemo.controller;

import com.deepsense.springbootdemo.model.Book;
import com.deepsense.springbootdemo.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BookControllerImpl implements BookController {

    private final BookRepository bookRepository;

    public BookControllerImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @GetMapping("/book")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(value = "id") Long bookId) throws EntityNotFoundException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found for this id :: " + bookId));
        return ResponseEntity.ok().body(book);
    }

    @Override
    @PostMapping("/book")
    public Book createBook(@RequestBody Book book) {
        // Add defensive check to prevent override existing book in DB
        return bookRepository.save(book);
    }

    @Override
    @DeleteMapping("/book/{id}")
    public Map<String, Boolean> deleteBook(@PathVariable(value = "id") Long bookId)
            throws EntityNotFoundException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found for this id :: " + bookId));
        bookRepository.delete(book);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
