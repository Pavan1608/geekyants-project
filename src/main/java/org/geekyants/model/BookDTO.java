package org.geekyants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.geekyants.entity.Book;

import java.util.UUID;

@Data
@AllArgsConstructor
public class BookDTO {

    private UUID id;
    private String title;
    private String author;
    private Book.BookCategory category;
    private Boolean isAvailable;
    private Integer totalCopies;
    private Integer availableCopies;
}
