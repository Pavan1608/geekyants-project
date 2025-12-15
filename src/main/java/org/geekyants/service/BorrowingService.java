package org.geekyants.service;

import jakarta.transaction.Transactional;
import org.geekyants.entity.BorrowRecord;
import org.geekyants.model.BorrowRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BorrowingService {
    private final BookService bookService;
    private final BorrowerService borrowerService;
    private final BorrowRecordService borrowRecordService;

    public BorrowingService(BookService bookService, BorrowerService borrowerService, BorrowRecordService borrowRecordService) {
        this.bookService = bookService;
        this.borrowerService = borrowerService;
        this.borrowRecordService = borrowRecordService;
    }

    @Transactional
    public void borrowBook(BorrowRequest borrowRequest) {
        // Validate borrower
        borrowerService.validateBorrower(borrowRequest.getBorrowerId());

        // Check book availability
        bookService.checkBookAvailability(borrowRequest.getBookId());

        // Process borrowing logic (e.g., create borrow record, update book availability)
        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setBookId(borrowRequest.getBookId());
        borrowRecord.setBorrowerId(borrowRequest.getBorrowerId());
        borrowRecord.setBorrowDate(borrowRequest.getBorrowDate());
        borrowRecord.setDueDate(borrowRequest.getBorrowDate().plusDays(14));

        // Save borrow record and update book availability
        borrowRecordService.saveBorrowRecord(borrowRecord);
        bookService.updateBookAvailability(borrowRequest.getBookId(), -1);

    }

    @Transactional
    public void returnBook(BorrowRequest borrowRequest) {
        // Validate borrow record
        BorrowRecord borrowRecord = borrowRecordService.getActiveBorrowRecord(
                borrowRequest.getBookId(), borrowRequest.getBorrowerId());

        // Process return logic (e.g., update borrow record, calculate fines, update book availability)
        borrowRecord.setReturnDate(borrowRequest.getReturnDate());
        if (borrowRequest.getReturnDate().isAfter(borrowRecord.getDueDate())) {
            long daysLate = java.time.temporal.ChronoUnit.DAYS.between(
                    borrowRecord.getDueDate(), borrowRequest.getReturnDate());
            borrowRecord.setFineAmount(BigDecimal.valueOf(daysLate * 1.0)); // Assuming $1 fine per day late
        } else {
            borrowRecord.setFineAmount(BigDecimal.valueOf(0.0));
        }

        // Save updated borrow record and update book availability
        borrowRecordService.saveBorrowRecord(borrowRecord);
        bookService.updateBookAvailability(borrowRequest.getBookId(), 1);
    }
}
