package org.geekyants.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.geekyants.entity.Book;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailabilitySummaryDTO {
    private Book.BookCategory category;
    private Long availableBooks;
    private Long totalBooks;
    private Double availabilityPercentage;
}
