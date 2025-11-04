package org.geekyants.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.geekyants.entity.BookUpdateRequest;
import org.geekyants.model.AvailabilitySummaryDTO;
import org.geekyants.model.BookDTO;
import org.geekyants.model.BorrowerDTO;
import org.geekyants.model.BorrowerRecordDTO;
import org.geekyants.service.BookService;
import org.geekyants.service.BorrowerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/borrowers")
@Tag(name = "Borrower", description = " Borrowers management APIs")
public class BorrowerController {


    private  final BorrowerService borrowerService;

    public BorrowerController(BorrowerService bookService) {
        this.borrowerService = bookService;
    }


    @PostMapping
    @Operation(summary = "Register new borrower")
    public ResponseEntity<BorrowerDTO> addBook(@Valid @RequestBody BorrowerDTO request) {
        BorrowerDTO response = borrowerService.registerBorrower(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/records")
    @Operation(
            summary = "Get borrower's borrow history",
            description = "Returns all borrow records (past and present) for a specific borrower"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Records retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Borrower not found")
    })
    public ResponseEntity<BorrowerDTO> getBorrowerRecords(
            @Parameter(description = "Borrower UUID") @PathVariable UUID id) {
        BorrowerDTO records = borrowerService.getBorrowerRecords(id);
        return ResponseEntity.ok(records);
    }

}

