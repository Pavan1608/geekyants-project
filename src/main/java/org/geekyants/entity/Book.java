package org.geekyants.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
public class Book {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String title;

    public Book(UUID id, String title, String author, BookCategory category, Boolean isAvailable, Integer totalCopies, Integer availableCopies, Boolean deleted, Long version) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isAvailable = isAvailable;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
        this.deleted = deleted;
        this.version = version;
    }

    public Book() {
    }

    ;

    @Column(nullable = false)
    private String author;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookCategory category;

    @Column(nullable = false)
    private Boolean isAvailable = true;

    @Column(nullable = false)
    private Integer totalCopies = 0;

    @Column(nullable = false)
    private Integer availableCopies = 0;

    @Column(nullable = false)
    private Boolean deleted = false;

    @Version
    private Long version; // For optimistic locking

    public enum BookCategory {
        FICTION, TECH, HISTORY, SCIENCE, BIOGRAPHY, FANTASY, MYSTERY, ROMANCE, SELF_HELP, OTHER
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public void setTotalCopies(Integer totalCopies) {
        this.totalCopies = totalCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public BookCategory getCategory() {
        return category;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public Integer getTotalCopies() {
        return totalCopies;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public Long getVersion() {
        return version;
    }

    public void borrowCopy() {
        if (availableCopies > 0) {
            availableCopies--;
            isAvailable = availableCopies > 0;
        }
    }

    public void returnCopy() {
        if (availableCopies < totalCopies) {
            availableCopies++;
            isAvailable = true;
        }
    }
}

