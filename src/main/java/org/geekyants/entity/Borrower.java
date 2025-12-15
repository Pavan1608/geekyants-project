package org.geekyants.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Borrower {
    @OneToMany(mappedBy = "borrower")
    @JsonManagedReference
    public List<BorrowRecord> borrowRecords;
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

    @PrePersist
    public void setMaxBorrowLimit() {
        if (maxBorrowLimit == null) {
            maxBorrowLimit = membershipType.getDefaultLimit();
        }
    }

    public enum MembershipType {
        BASIC(2),
        PREMIUM(3);
        final Integer value;

        MembershipType(int borrowLimit) {
            this.value = borrowLimit;
        }

        public int getDefaultLimit() {
            return value;
        }
    }
}
