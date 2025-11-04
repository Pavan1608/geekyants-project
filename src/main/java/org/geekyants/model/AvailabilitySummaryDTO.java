package org.geekyants.model;


import org.geekyants.entity.Book;

import java.util.Objects;

public class AvailabilitySummaryDTO {
    private Book.BookCategory category;
    private Long availableBooks;
    private Long totalBooks;
    private Double availabilityPercentage;

    public AvailabilitySummaryDTO(Book.BookCategory category, Long availableBooks, Long totalBooks, Double availabilityPercentage) {
        this.category = category;
        this.availableBooks = availableBooks;
        this.totalBooks = totalBooks;
        this.availabilityPercentage = availabilityPercentage;
    }

    public Book.BookCategory getCategory() {
        return category;
    }

    public void setCategory(Book.BookCategory category) {
        this.category = category;
    }

    public Long getAvailableBooks() {
        return availableBooks;
    }

    public void setAvailableBooks(Long availableBooks) {
        this.availableBooks = availableBooks;
    }

    public Long getTotalBooks() {
        return totalBooks;
    }

    public void setTotalBooks(Long totalBooks) {
        this.totalBooks = totalBooks;
    }

    public Double getAvailabilityPercentage() {
        return availabilityPercentage;
    }

    public void setAvailabilityPercentage(Double availabilityPercentage) {
        this.availabilityPercentage = availabilityPercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AvailabilitySummaryDTO that = (AvailabilitySummaryDTO) o;
        return category == that.category && Objects.equals(availableBooks, that.availableBooks) && Objects.equals(totalBooks, that.totalBooks) && Objects.equals(availabilityPercentage, that.availabilityPercentage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, availableBooks, totalBooks, availabilityPercentage);
    }
}
