package org.geekyants.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "borrow_records")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "bookId", insertable = false, updatable = false)
//    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrower_id", insertable = false, updatable = false)
    @JsonBackReference
    private Borrower borrower;
}

