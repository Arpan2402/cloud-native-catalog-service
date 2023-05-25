package com.arpanm.catalogservice;

import com.arpanm.catalogservice.dao.BookRepository;
import com.arpanm.catalogservice.domain.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class CatalogServiceApplicationTests {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		this.bookRepository.save(
				new Book("1234567", "Sample Tile", "Sample Author", 100d));
	}

	@Test
	void testViewBookByValidIsbnShouldReturn200() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/books/1234567"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value("1234567"));
	}

}
