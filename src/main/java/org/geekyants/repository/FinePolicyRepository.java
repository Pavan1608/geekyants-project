package org.geekyants.repository;

import org.geekyants.entity.Book;
import org.geekyants.entity.FinePolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FinePolicyRepository extends JpaRepository<FinePolicy, Long> {

    Optional<FinePolicy> findByCategory(Book.BookCategory category);
}
