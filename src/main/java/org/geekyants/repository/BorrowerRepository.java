package org.geekyants.repository;

import org.geekyants.entity.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, UUID> {

    Optional<Borrower> findByEmail(String email);

    boolean existsByEmail(String email);

//    @Query("SELECT new com.library.management.dto.OverdueBorrowerResponse(" +
//            "b.id, b.name, b.email, " +
//            "CAST(COUNT(br.id) AS int), " +
//            "MIN(br.dueDate)) " +
//            "FROM Borrower b " +
//            "JOIN BorrowRecord br ON br.borrowerId = b.id " +
//            "WHERE br.returnDate IS NULL AND br.dueDate < :today " +
//            "GROUP BY b.id, b.name, b.email")
//    List<OverdueBorrowerResponse> findBorrowersWithOverdueBooks(@Param("today") LocalDate today);
}