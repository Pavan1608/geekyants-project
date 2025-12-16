package org.geekyants.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.geekyants.entity.Book;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class BorrowRecordDTO {
    private UUID id;
    private UUID bookId;
    private String title;
    private String author;
    private Book.BookCategory category;
    private UUID borrowerId;
    private String name;
    private String email;
    private LocalDate borrowDate;
    private LocalDate dueDate;

    public BorrowRecordDTO(
            UUID id,
            UUID bookId,
            String title,
            String author,
            Book.BookCategory category, // Ensure this exact enum/type is used
            UUID borrowerId,
            String name,
            String email,
            LocalDate borrowDate,
            LocalDate dueDate) {
        this.id = id;
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.borrowerId = borrowerId;
        this.name = name;
        this.email = email;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }


}
