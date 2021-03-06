package com.deepsense.springbootdemo;

import com.deepsense.springbootdemo.model.Book;
import com.deepsense.springbootdemo.repository.BookRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class DataLoader implements ApplicationRunner {

    private BookRepository bookRepository;

    @Autowired
    public DataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void run(ApplicationArguments args) {
        bookRepository.save(new Book(1, "Sam Newman", "Building Microservices", 300, false));
        bookRepository.save(new Book(2,"Robert Martin", "Clean Code", 400, true));
        bookRepository.save(new Book(3, "Robert Martin", "Clean Architecture", 433, true));

        bookRepository.findAll().forEach(x -> log.info(x.toString()));
    }
}