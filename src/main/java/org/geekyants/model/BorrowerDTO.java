package org.geekyants.model;

import org.geekyants.entity.BorrowRecord;
import org.geekyants.entity.Borrower;
import java.util.List;

import java.util.UUID;

public class BorrowerDTO {
    private UUID id;
    private String name;
    private String email;
    private Borrower.MembershipType membershipType;
    private Integer maxBorrowLimit;
    private List<BorrowRecord> recordHistory;

    public List<BorrowRecord> getRecordHistory() {
        return recordHistory;
    }

    public void setRecordHistory(List<BorrowRecord> recordHistory) {
        this.recordHistory = recordHistory;
    }

    public BorrowerDTO(UUID id, String name, String email, Borrower.MembershipType membershipType, Integer maxBorrowLimit, List<BorrowRecord> recordHistory) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.membershipType = membershipType;
        this.maxBorrowLimit = maxBorrowLimit;
        this.recordHistory = recordHistory;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Borrower.MembershipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(Borrower.MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    public Integer getMaxBorrowLimit() {
        return maxBorrowLimit;
    }

    public void setMaxBorrowLimit(Integer maxBorrowLimit) {
        this.maxBorrowLimit = maxBorrowLimit;
    }
}