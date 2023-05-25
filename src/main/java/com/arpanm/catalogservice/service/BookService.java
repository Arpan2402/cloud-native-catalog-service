package com.arpanm.catalogservice.service;

import com.arpanm.catalogservice.domain.Book;

public interface BookService {
    Iterable<Book> getAllBooksFromCatalog();
    Book viewBookDetailsFromCatalog(String isbn);
    Book addNewBookInCatalog(Book book);
    Book editBookInCatalog(String isbn, Book book);
    void removeBookFromCatalog(String isbn);
}
