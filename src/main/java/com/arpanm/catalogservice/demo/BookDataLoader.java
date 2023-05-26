package com.arpanm.catalogservice.demo;

import com.arpanm.catalogservice.domain.Book;
import com.arpanm.catalogservice.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("loadTestData")
@RequiredArgsConstructor
@Slf4j
public class BookDataLoader {

    private final BookService bookService;

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookData() {
        log.info("Loading Initial Books to Catalog for testing purposes...");
        this.bookService.addNewBookInCatalog(new Book("1234567", "Title1", "Author1", 1000d));
        this.bookService.addNewBookInCatalog(new Book("1234568", "Title2", "Author2", 2000d));
        this.bookService.addNewBookInCatalog(new Book("1234569", "Title3", "Author3", 3000d));
        this.bookService.addNewBookInCatalog(new Book("2345671", "Title4", "Author4", 4000d));
        log.info("Loading Initial Books to Catalog Complete.");
    }
}
