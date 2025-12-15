package org.geekyants.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "fine_policies")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FinePolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Book.BookCategory category;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal finePerDay;
}
