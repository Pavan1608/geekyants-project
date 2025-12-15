package org.geekyants.repository;

import org.geekyants.entity.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, UUID> {

    Optional<Borrower> findByEmail(String email);

    boolean existsByEmail(String email);


    @Query("SELECT b " +
            "FROM Borrower b " +
            "JOIN BorrowRecord br ON br.borrowerId = b.id " +
            "WHERE br.returnDate IS NULL AND br.dueDate < :today ")
    List<Borrower> findBorrowersWithOverdueBooks(@Param("today") LocalDate today);
}