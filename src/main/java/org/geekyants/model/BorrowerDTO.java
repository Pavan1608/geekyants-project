package org.geekyants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.geekyants.entity.BorrowRecord;
import org.geekyants.entity.Borrower;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowerDTO {
    private UUID id;
    private String name;
    private String email;
    private Borrower.MembershipType membershipType;
    private Integer maxBorrowLimit;
    private List<BorrowRecord> recordHistory;
}