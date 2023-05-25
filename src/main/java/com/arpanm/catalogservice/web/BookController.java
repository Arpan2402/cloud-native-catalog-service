package com.arpanm.catalogservice.web;

import com.arpanm.catalogservice.domain.Book;
import com.arpanm.catalogservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public Iterable<Book> viewAllBooksInCatalog() {
        return this.bookService.getAllBooksFromCatalog();
    }

    @GetMapping(value = "/{isbn}")
    public Book viewBookWithIsbn(@PathVariable String isbn) {
        return this.bookService.viewBookDetailsFromCatalog(isbn);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createNewBookInCatalog(@Valid @RequestBody Book book) {
        return this.bookService.addNewBookInCatalog(book);
    }

    @PutMapping(value = "/{isbn}")
    public Book editBookInCatalog(@PathVariable String isbn, @Valid @RequestBody Book book) {
        return this.bookService.editBookInCatalog(isbn, book);
    }

    @DeleteMapping(value = "/{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookInCatalog(@PathVariable String isbn) {
        this.bookService.removeBookFromCatalog(isbn);
    }
}
