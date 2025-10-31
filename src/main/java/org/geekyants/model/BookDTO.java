package org.geekyants.model;

import lombok.*;
import org.geekyants.entity.Book;

import java.util.Objects;
import java.util.UUID;

public class BookDTO {

        private UUID id;
        private String title;
        private String author;
        private Book.BookCategory category;
        private Boolean isAvailable;
        private Integer totalCopies;
        private Integer availableCopies;

       public BookDTO (){};

        public BookDTO(UUID id, String title, String author, Book.BookCategory category, Boolean isAvailable, Integer totalCopies, Integer availableCopies) {
                this.id = id;
                this.title = title;
                this.author = author;
                this.category = category;
                this.isAvailable = isAvailable;
                this.totalCopies = totalCopies;
                this.availableCopies = availableCopies;
        }

        public UUID getId() {
                return id;
        }

        public void setId(UUID id) {
                this.id = id;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getAuthor() {
                return author;
        }

        public void setAuthor(String author) {
                this.author = author;
        }

        public Book.BookCategory getCategory() {
                return category;
        }

        public void setCategory(Book.BookCategory category) {
                this.category = category;
        }

        public Boolean getAvailable() {
                return isAvailable;
        }

        public void setAvailable(Boolean available) {
                isAvailable = available;
        }

        public Integer getTotalCopies() {
                return totalCopies;
        }

        public void setTotalCopies(Integer totalCopies) {
                this.totalCopies = totalCopies;
        }

        public Integer getAvailableCopies() {
                return availableCopies;
        }

        public void setAvailableCopies(Integer availableCopies) {
                this.availableCopies = availableCopies;
        }

        @Override
        public boolean equals(Object o) {
                if (o == null || getClass() != o.getClass()) return false;
                BookDTO bookDTO = (BookDTO) o;
                return Objects.equals(id, bookDTO.id) && Objects.equals(title, bookDTO.title) && Objects.equals(author, bookDTO.author) && category == bookDTO.category && Objects.equals(isAvailable, bookDTO.isAvailable) && Objects.equals(totalCopies, bookDTO.totalCopies) && Objects.equals(availableCopies, bookDTO.availableCopies);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, title, author, category, isAvailable, totalCopies, availableCopies);
        }
}
