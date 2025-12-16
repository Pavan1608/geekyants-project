package org.geekyants.service;

import lombok.extern.slf4j.Slf4j;
import org.geekyants.entity.BorrowRecord;
import org.geekyants.model.BorrowRecordDTO;
import org.geekyants.repository.BorrowRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
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

    public List<BorrowRecordDTO> getAllActiveBorrowRecords() {
        return borrowRecordRepository.getAllActiveBorrowRecords();
    }
}
