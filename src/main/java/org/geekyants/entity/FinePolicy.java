package org.geekyants.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "fine_policies")
public class FinePolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Book.BookCategory category;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal finePerDay;

    public FinePolicy(Long id, Book.BookCategory category, BigDecimal finePerDay) {
        this.id = id;
        this.category = category;
        this.finePerDay = finePerDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book.BookCategory getCategory() {
        return category;
    }

    public void setCategory(Book.BookCategory category) {
        this.category = category;
    }

    public BigDecimal getFinePerDay() {
        return finePerDay;
    }

    public void setFinePerDay(BigDecimal finePerDay) {
        this.finePerDay = finePerDay;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FinePolicy that = (FinePolicy) o;
        return Objects.equals(id, that.id) && Objects.equals(category, that.category) && Objects.equals(finePerDay, that.finePerDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, finePerDay);
    }
}
