package com.arpanm.catalogservice.dao.impl;

import com.arpanm.catalogservice.dao.BookRepository;
import com.arpanm.catalogservice.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryBookRepositoryImpl implements BookRepository {

    private ConcurrentHashMap<String, Book> books = new ConcurrentHashMap<>();

    @Override
    public Iterable<Book> findAll() {
        return this.books.values();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return existsByIsbn(isbn) ? Optional.of(this.books.get(isbn)) : Optional.empty();
    }

    @Override
    public Book save(Book book) {
        this.books.put(book.getIsbn(), book);
        return book;
    }

    @Override
    public void remove(String isbn) {
        this.books.remove(isbn);
    }

    private boolean existsByIsbn(String isbn) {
        return this.books.get(isbn) != null;
    }
}
