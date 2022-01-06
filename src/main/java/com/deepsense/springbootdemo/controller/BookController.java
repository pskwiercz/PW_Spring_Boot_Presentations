package com.deepsense.springbootdemo.controller;

import com.deepsense.springbootdemo.model.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface BookController {

    ResponseEntity<Book> getBookById(Long bookId);

    List<Book> getAllBooks();

    Book createBook(Book book);

    Map<String, Boolean> deleteBook(Long bookId);

}
