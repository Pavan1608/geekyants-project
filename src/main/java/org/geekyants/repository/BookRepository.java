package org.geekyants.repository;

import org.geekyants.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    public List<Book> findAllById(UUID id);

    Book findByTitleAndAuthor(String title, String author);

    List<Book> findByCategoryOrAuthor(Book.BookCategory category, String author);

    @Query("SELECT b FROM Book b WHERE " + // Changed 'book' to 'Book'
            "(:category IS NULL OR b.category = :category) AND " +
            "(:isAvailable IS NULL OR b.isAvailable = :isAvailable)")
    List<Book> findAllByOptionalCategoryAndAvailability(@Param("category") Book.BookCategory category, @Param("isAvailable") Boolean isAvailable);
}
