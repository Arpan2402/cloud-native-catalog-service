package com.arpanm.catalogservice.service.impl;

import com.arpanm.catalogservice.dao.BookRepository;
import com.arpanm.catalogservice.domain.Book;
import com.arpanm.catalogservice.exception.BookAlreadyExistsException;
import com.arpanm.catalogservice.exception.BookNotFoundException;
import com.arpanm.catalogservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Book> getAllBooksFromCatalog() {
        return this.bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Book viewBookDetailsFromCatalog(String isbn) {
        return this.bookRepository
                .findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    @Override
    public Book addNewBookInCatalog(Book book) {
        Optional<Book> optional = this.bookRepository.findByIsbn(book.getIsbn());
        if(optional.isPresent()) throw new BookAlreadyExistsException(book.getIsbn());

        return this.bookRepository.save(book);
    }

    @Override
    public Book editBookInCatalog(String isbn, Book book) {
        return this.bookRepository.findByIsbn(isbn).map(bookData -> {
            Book updatedBook = new Book(bookData.getId(),
                                        bookData.getVersion(),
                                        bookData.getIsbn(),
                                        book.getTitle(),
                                        book.getAuthor(),
                                        book.getPrice(),
                                        bookData.getCreatedDate(),
                                        bookData.getLastUpdatedDate(),
                                        book.getPublisher());
            return this.bookRepository.save(updatedBook);
        }).orElseThrow(() -> new BookNotFoundException(isbn));
    }

    @Override
    public void removeBookFromCatalog(String isbn) {
        this.bookRepository.deleteByIsbn(isbn);
    }
}
