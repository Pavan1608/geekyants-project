package org.geekyants.repository;

import org.geekyants.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    public List<Book> findAllById(UUID id);

    Book findByTitleAndAuthorAndDeletedFalse(String title, String author);
}
