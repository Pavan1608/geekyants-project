package org.geekyants.repository;

import org.geekyants.entity.Book;
import org.geekyants.entity.FinePolicy;
import org.geekyants.exception.LibraryManagementException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FinePolicyService {
    private final FinePolicyRepository finePolicyRepository;

    public FinePolicyService(FinePolicyRepository finePolicyRepository) {
        this.finePolicyRepository = finePolicyRepository;
    }

    public BigDecimal getFinePerDayByCategory(Book.BookCategory category) {
        return finePolicyRepository.findByCategory(category).map(
                FinePolicy::getFinePerDay).orElseThrow(
                () -> new LibraryManagementException("Fine policy not found for category: " + category)
        );
    }
}
