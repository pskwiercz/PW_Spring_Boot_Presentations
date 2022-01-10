package com.deepsense.springbootdemo.controller;

import com.deepsense.springbootdemo.model.Book;
import com.deepsense.springbootdemo.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookControllerImpl.class)
class BookControllerImplTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookRepository bookRepository;

    @Test
    public void whenValidInput_thenReturns200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/book/")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void getBookTest() throws Exception {
        Book book = new Book(
                1l,
                "Stephen King",
                "Sklepik z marzeniami",
                100,
                false
        );

        given(bookRepository.findById(1l)).willReturn(java.util.Optional.of(book));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/book/{id}", 1l)
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author").value("Stephen King"));
    }

    @Test
    public void createBookTest() throws Exception {
        Book book = new Book(
                5l,
                "Lee Child",
                "Jack Reacher - jednym strzałem",
                120,
                false
        );
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/book")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.author").value("Lee Child"))
                .andExpect(jsonPath("$.title").value("Jack Reacher - jednym strzałem"))
                .andExpect(jsonPath("$.pages").value(120));
    }
}