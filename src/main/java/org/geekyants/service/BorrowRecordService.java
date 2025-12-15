package org.geekyants.service;

import org.geekyants.entity.BorrowRecord;
import org.geekyants.repository.BorrowRecordRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BorrowRecordService {

    private final BorrowRecordRepository borrowRecordRepository;

    public BorrowRecordService(BorrowRecordRepository borrowRecordRepository) {
        this.borrowRecordRepository = borrowRecordRepository;
    }

    public void saveBorrowRecord(BorrowRecord record) {
        borrowRecordRepository.save(record);
    }

    public BorrowRecord getActiveBorrowRecord(UUID bookId, UUID borrowerId) {
        return borrowRecordRepository.findByBookIdAndBorrowerIdAndReturnDateIsNull(bookId, borrowerId);
    }
}
