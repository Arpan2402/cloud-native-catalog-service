package com.arpanm.catalogservice;

import com.arpanm.catalogservice.domain.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
public class CatalogServiceApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void findBookByIsbn() {
        Book book = Book.of("1234567", "Title1", "Author1", 1000d);
        Book expectedBook = webTestClient
                .post()
                .uri("/v1/books")
                .bodyValue(book)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(Book.class).value(book1 -> Assertions.assertThat(book1).isNotNull())
                .returnResult().getResponseBody();

        webTestClient
                .get()
                .uri("/v1/books/" + expectedBook.getIsbn())
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void createBook() {
        Book book = Book.of("1234568", "Title1", "Author1", 1000d);
        webTestClient
                .post()
                .uri("/v1/books")
                .bodyValue(book)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(Book.class).value(book1 -> Assertions.assertThat(book1).isNotNull());
    }
}
