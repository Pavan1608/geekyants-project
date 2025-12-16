package org.geekyants.repository;

import org.geekyants.entity.BorrowRecord;
import org.geekyants.model.BorrowRecordDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, UUID> {
    List<BorrowRecord> findActiveRecordsByBookId(UUID id);

    BorrowRecord findByBookIdAndBorrowerIdAndReturnDateIsNull(UUID bookId, UUID borrowerId);

    boolean existsByBookIdAndReturnDateIsNull(UUID id);

    @Query("SELECT new org.geekyants.model.BorrowRecordDTO(" +
            "br.id, b.id, b.title, b.author, b.category, " +
            "bo.id, bo.name, bo.email, " +
            "br.borrowDate, br.dueDate) " +
            "FROM BorrowRecord br " +
            "JOIN Book b ON br.bookId = b.id " +    // HQL supports ON clause for unrelated entities
            "JOIN Borrower bo ON br.borrowerId = bo.id " +
            "WHERE br.returnDate IS NULL")
    List<BorrowRecordDTO> getAllActiveBorrowRecords();
}
