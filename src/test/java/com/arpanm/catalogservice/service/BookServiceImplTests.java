package com.arpanm.catalogservice.service;

import com.arpanm.catalogservice.dao.BookRepository;
import com.arpanm.catalogservice.domain.Book;
import com.arpanm.catalogservice.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.Arrays;

@ExtendWith(SpringExtension.class)
class BookServiceImplTests {

	@TestConfiguration
	static class EmployeeServiceImplTestContextConfiguration {
		@Bean
		public BookService employeeService(BookRepository bookRepository) {
			return new BookServiceImpl(bookRepository);
		}
	}

	@Autowired
	private BookService bookService;

	@MockBean
	private BookRepository bookRepository;

	@BeforeEach
	public void setup() {
		BDDMockito.when(bookRepository.findAll())
				.thenReturn(Arrays.asList(
						new Book(1l,
								0,
								"1234567",
								"Sample Title",
								"Sample Author",
								1000d,
								Instant.now(),
								Instant.now())));
	}

	@Test
	void getAllBooksFromCatalogShouldPass() throws Exception {
		Iterable<Book> allBooksFromCatalog = this.bookService.getAllBooksFromCatalog();
		Assertions.assertNotNull(allBooksFromCatalog);
		Assertions.assertEquals(allBooksFromCatalog.iterator().next().getIsbn(), "1234567");
	}
}
