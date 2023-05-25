package com.arpanm.catalogservice.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
public class Book {

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
}
