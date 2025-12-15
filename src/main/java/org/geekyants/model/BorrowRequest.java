package org.geekyants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowRequest {
    private UUID bookId;
    private UUID borrowerId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}
