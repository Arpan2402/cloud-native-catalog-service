package com.arpanm.catalogservice.dao;

import com.arpanm.catalogservice.domain.Book;

import java.util.Optional;

public interface BookRepository {
    Iterable<Book> findAll();
    Optional<Book> findByIsbn(String isbn);
    Book save(Book book);
    void remove(String isbn);
}
