package org.geekyants.repository;

import org.geekyants.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, UUID> {
    List<BorrowRecord> findActiveRecordsByBookId(UUID id);

    BorrowRecord findByBookIdAndBorrowerIdAndReturnDateIsNull(UUID bookId, UUID borrowerId);

    boolean existsByBookIdAndReturnDateIsNull(UUID id);
}
