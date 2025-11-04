package org.geekyants.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class BorrowerRecordDTO {
        private UUID id;
        private UUID bookId;
        private UUID borrowerId;
        private String borrowerName;
        private String borrowerEmail;
        private LocalDate borrowDate;
        private LocalDate dueDate;
        private LocalDate returnDate;
        private BigDecimal fineAmount;
    }

