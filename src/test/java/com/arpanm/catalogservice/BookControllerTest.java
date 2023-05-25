package com.arpanm.catalogservice;

import com.arpanm.catalogservice.domain.Book;
import com.arpanm.catalogservice.exception.BookNotFoundException;
import com.arpanm.catalogservice.service.BookService;
import com.arpanm.catalogservice.web.BookController;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private BookController bookController;

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testWhenIsbnIsAbsentShouldReturn404() throws Exception {
        String isbn = "1234567";
        BDDMockito.given(bookService.viewBookDetailsFromCatalog(isbn))
                .willThrow(BookNotFoundException.class);
        mockMvc
                .perform(MockMvcRequestBuilders.get("/v1/books/" + isbn))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testWhenViewAllBooksShouldReturnAll200() throws Exception {
        BDDMockito.when(bookService.getAllBooksFromCatalog())
                .thenReturn(Arrays.asList(
                        new Book("1234567", "Sample Title", "Sample Author", 1000d)));
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/books"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
