package org.geekyants.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.geekyants.entity.Book;
import org.geekyants.entity.BookUpdateRequest;
import org.geekyants.model.AvailabilitySummaryDTO;
import org.geekyants.model.BookDTO;
import org.geekyants.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
@Tag(name = "Books", description = "Book management APIs")
public class BooksController {


    private  final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping
    @Operation(summary = "Add a new book or increase copies")
    public ResponseEntity<BookDTO> addBook(@Valid @RequestBody BookDTO request) {
        BookDTO response = bookService.addBook(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all books with filters")
    public ResponseEntity<List<BookDTO>> getBooks(
            @RequestParam(required = false) Book.BookCategory category,
            @RequestParam(required = false) Boolean available,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy) {


        List<BookDTO> books = bookService.getBooks(category, available);
        return ResponseEntity.ok(books);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update book metadata")
    public ResponseEntity<BookDTO> updateBook(
            @PathVariable UUID id,
            @Valid @RequestBody BookUpdateRequest request) {
        BookDTO response = bookService.updateBook(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Soft delete a book")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/similar/{id}")
    @Operation(summary = "Get similar books by category or author")
    public ResponseEntity<List<BookDTO>> getSimilarBooks(@PathVariable UUID id) {
        List<BookDTO> books = bookService.getSimilarBooks(id);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/availability-summary")
    @Operation(summary = "Get availability summary by category")
    public ResponseEntity<List<AvailabilitySummaryDTO>> getAvailabilitySummary() {
        List<AvailabilitySummaryDTO> summary = bookService.getAvailabilitySummary();
        return ResponseEntity.ok(summary);
    }
}

