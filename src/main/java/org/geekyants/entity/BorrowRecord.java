package org.geekyants.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "borrow_records")
public class BorrowRecord {
    @Id
    @UuidGenerator
    private UUID id;

    @Column(nullable = false)
    private UUID bookId;

    @Column(name = "borrower_id", nullable = false)
    private UUID borrowerId;

    @Column(nullable = false)
    private LocalDate borrowDate;

    @Column(nullable = false)
    private LocalDate dueDate;

    private LocalDate returnDate;

    @Column(precision = 10, scale = 2)
    private BigDecimal fineAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId", insertable = false, updatable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrower_id", insertable = false, updatable = false)
    private Borrower borrower;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public UUID getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(UUID borrowerId) {
        this.borrowerId = borrowerId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public BigDecimal getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(BigDecimal fineAmount) {
        this.fineAmount = fineAmount;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BorrowRecord that = (BorrowRecord) o;
        return Objects.equals(id, that.id) && Objects.equals(bookId, that.bookId) && Objects.equals(borrowerId, that.borrowerId) && Objects.equals(borrowDate, that.borrowDate) && Objects.equals(dueDate, that.dueDate) && Objects.equals(returnDate, that.returnDate) && Objects.equals(fineAmount, that.fineAmount) && Objects.equals(book, that.book) && Objects.equals(borrower, that.borrower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookId, borrowerId, borrowDate, dueDate, returnDate, fineAmount, book, borrower);
    }
}

