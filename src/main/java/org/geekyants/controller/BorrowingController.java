package org.geekyants.controller;

import org.geekyants.model.BorrowRequest;
import org.geekyants.service.BorrowingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class BorrowingController {
    private final BorrowingService borrowingService;

    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @PostMapping("/borrow")
    public String borrowBook(@RequestBody BorrowRequest borrowRequest) {

        borrowingService.borrowBook(borrowRequest);
        return "Book borrowed successfully";
    }

    @PostMapping("/return")
    public String returnBook(@RequestBody BorrowRequest borrowRequest) {
        borrowingService.returnBook(borrowRequest);
        return "Book returned successfully";
    }
}
