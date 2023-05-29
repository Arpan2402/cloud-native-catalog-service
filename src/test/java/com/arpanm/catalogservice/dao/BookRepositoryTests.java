package com.arpanm.catalogservice.dao;

import com.arpanm.catalogservice.config.DataConfig;
import com.arpanm.catalogservice.domain.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@DataJdbcTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
public class BookRepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;

    @Test
    public void findBookByIsbnShouldPass() {
        String isbn = "1234567";
        Book book1 = Book.of(isbn, "Title1", "Author1", 1000d);

        jdbcAggregateTemplate.insert(book1);

        Optional<Book> optional = bookRepository.findByIsbn(isbn);
        Assertions.assertThat(optional).isPresent();
        Assertions.assertThat(optional.get().getIsbn()).isEqualTo(isbn);
    }
}
