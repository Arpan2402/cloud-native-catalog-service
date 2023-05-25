package com.arpanm.catalogservice.exception;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String isbn) {
        super(String.format("Book with isbn %s already exists in catalog", isbn));
    }
}
