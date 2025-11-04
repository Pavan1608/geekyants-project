package org.geekyants.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Borrower {
        @Id
        @UuidGenerator
        private UUID id;

        @Column(nullable = false)
        private String name;

        @Column(nullable = false, unique = true)
        private String email;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private MembershipType membershipType;

        @Column(nullable = false)
        private Integer maxBorrowLimit;

        @OneToMany
        @JoinColumn(name="borrower_id")
        public List<BorrowRecord> borrowRecords;

    public List<BorrowRecord> getBorrowRecords() {
        return borrowRecords;
    }

    public void setBorrowRecords(List<BorrowRecord> borrowRecords) {
        this.borrowRecords = borrowRecords;
    }

    @PrePersist
        public void setMaxBorrowLimit() {
            if (maxBorrowLimit == null) {
                maxBorrowLimit = membershipType.getDefaultLimit();
            }
        }

    public enum MembershipType {
        BASIC(2),
        PREMIUM(3);
        final Integer value ;
        MembershipType(int borrowLimit) {
            this.value = borrowLimit;
        }
        public int getDefaultLimit() {
            return value;
        }
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

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    public Integer getMaxBorrowLimit() {
        return maxBorrowLimit;
    }

    public void setMaxBorrowLimit(Integer maxBorrowLimit) {
        this.maxBorrowLimit = maxBorrowLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Borrower borrower = (Borrower) o;
        return Objects.equals(id, borrower.id) && Objects.equals(name, borrower.name) && Objects.equals(email, borrower.email) && membershipType == borrower.membershipType && Objects.equals(maxBorrowLimit, borrower.maxBorrowLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, membershipType, maxBorrowLimit);
    }
}
