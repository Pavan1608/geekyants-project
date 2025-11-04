package org.geekyants.service;

import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.geekyants.entity.Borrower;
import org.geekyants.model.BorrowerDTO;
import org.geekyants.repository.BorrowerRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
@Service
public class BorrowerService {

    private final BorrowerRepository borrowerRepository;

    public BorrowerService(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }
    private static final Logger log = Logger.getLogger(BorrowerService.class.getName());

    @Transactional
    //@CacheEvict(value = "borrowers", allEntries = true)
    public BorrowerDTO registerBorrower(BorrowerDTO request) {
        log.info("Registering new borrower with email: "+ request.getEmail());

        // Validate email uniqueness
        if (borrowerRepository.existsByEmail(request.getEmail())) {
            log.info("Registration failed - Email already exists: {}" + request.getEmail());
            throw new ValidationException("Email already registered: " + request.getEmail());
        }

        // Create borrower entity
        Borrower borrower = new Borrower();
        borrower.setName(request.getName());
        borrower.setEmail(request.getEmail());
        borrower.setMembershipType(request.getMembershipType());
        borrower.setMaxBorrowLimit(request.getMembershipType().getDefaultLimit());

        // Save borrower
        Borrower savedBorrower = borrowerRepository.save(borrower);
        log.info("Successfully registered borrower with ID: {}"+ savedBorrower.getId());

        return toResponseDTO(savedBorrower);
    }

    public BorrowerDTO getBorrowerRecords(UUID id) {
      return borrowerRepository.findById(id)
                .map(this::toResponseDTO)
                .orElse(null);

    }

    private BorrowerDTO toResponseDTO(Borrower borrower) {
        return new BorrowerDTO(
                borrower.getId(),
                borrower.getName(),
                borrower.getEmail(),
                borrower.getMembershipType(),
                borrower.getMaxBorrowLimit(),
                borrower.getBorrowRecords()
        );

    }
}
