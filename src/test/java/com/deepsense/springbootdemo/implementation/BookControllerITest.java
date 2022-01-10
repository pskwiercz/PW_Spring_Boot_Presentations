package com.deepsense.springbootdemo.implementation;

import com.deepsense.springbootdemo.model.Book;
import com.deepsense.springbootdemo.repository.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerITest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void createBookTest() throws JSONException, JsonProcessingException {
        Book newBook = new Book(4, "", "", 100, false);

        String expected = OBJECT_MAPPER.writeValueAsString(newBook);

        ResponseEntity<String> response = this.testRestTemplate.postForEntity("/api/book", newBook, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test()
    public void getBookListTest() throws JSONException {
        String response = this.testRestTemplate.getForObject("/api/book", String.class);
        JSONAssert.assertEquals("[{id:1},{id:2},{id:3},{id:4}]", response, false);
    }
}
