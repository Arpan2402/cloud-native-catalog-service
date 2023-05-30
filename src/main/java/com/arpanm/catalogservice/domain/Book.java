package com.arpanm.catalogservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.time.Instant;

@Data
@AllArgsConstructor
public class Book {

    @Id
    private final Long id;

    @Version
    private final int version;

    @NotBlank(message = "The ISBN must not be blank")
    @Pattern(regexp = "[0-9]{7}", message = "The ISBN format must be valid")
    private final String isbn;

    @NotBlank(message = "The title can not be empty")
    private final String title;

    @NotBlank(message = "The author can not be empty")
    private final String author;

    @NotNull(message = "The price can not be empty")
    @Positive(message = "The price should be greater than 0")
    private final Double price;

    @CreatedDate
    private final Instant createdDate;

    @LastModifiedDate
    private final Instant lastUpdatedDate;

    private String publisher;

    public static Book of(String isbn, String title, String author, Double price, String publisher) {
        return new Book(null, 0, isbn, title, author, price, null, null, publisher);
    }
}
