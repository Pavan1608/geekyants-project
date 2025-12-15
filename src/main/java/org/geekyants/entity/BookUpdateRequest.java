package org.geekyants.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateRequest {
    private String title;
    private String author;
    private Book.BookCategory category;
    private Integer totalCopies;
    private Integer availableCopies;
}
