package com.arpanm.catalogservice.dao;

import com.arpanm.catalogservice.domain.Book;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);

    @Transactional
    @Modifying
    @Query("delete from Book b where b.isbn= :isbn")
    Long deleteByIsbn(String isbn);
}
