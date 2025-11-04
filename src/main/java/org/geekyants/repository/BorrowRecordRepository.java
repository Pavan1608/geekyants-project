package org.geekyants.repository;

import org.geekyants.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, UUID> {
    List<BorrowRecord> findActiveRecordsByBookId(UUID id);
}
