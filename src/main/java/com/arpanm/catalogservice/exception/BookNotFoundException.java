package com.arpanm.catalogservice.exception;

import com.arpanm.catalogservice.domain.Book;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException() {
        super();
    }

    public BookNotFoundException(String isbn) {
        super(String.format("Book with %s not found in Catalog", isbn));
    }
}
