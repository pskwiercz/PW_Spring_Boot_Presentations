package com.deepsense.springbootdemo.data;

import com.deepsense.springbootdemo.model.Book;
import com.deepsense.springbootdemo.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookRepositoryTest {

    private BookRepository bookRepository;

    @Autowired
    public void BookRepositoryTest(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @BeforeAll
    public void initDB() {
        bookRepository.save(new Book(1, "Sam Newman", "Building Microservices", 300, false));
        bookRepository.save(new Book(2,"Robert Martin", "Clean Code", 400, true));
        bookRepository.save(new Book(3, "Robert Martin", "Clean Architecture", 433, true));
    }

    @Test
    public void findAllTest() {
        List<Book> books = bookRepository.findAll();
        Assertions.assertEquals(3, books.size());
    }

    @Test
    public void bookPropertiesTest() {
        Book book = bookRepository.getById(2L);

        assertThat(book).hasFieldOrPropertyWithValue("title", "Clean Code");
        assertThat(book).hasFieldOrPropertyWithValue("author", "Robert Martin");
        assertThat(book).hasFieldOrPropertyWithValue("pages", 400);
        assertThat(book).hasFieldOrPropertyWithValue("discount", true);
    }

    @Test
    public void deleteBookTest() {
        bookRepository.deleteById(2L);

        List<Book> books = bookRepository.findAll();
        Assertions.assertEquals(2, books.size());

    }
}
