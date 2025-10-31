package org.geekyants.utility;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.geekyants.entity.Book;
import org.geekyants.entity.Borrower;
import org.geekyants.entity.FinePolicy;
import org.geekyants.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;
    private final FinePolicyRepository finePolicyRepository;

    public DataInitializer(BookRepository bookRepository, BorrowerRepository borrowerRepository, FinePolicyRepository finePolicyRepository) {
        this.bookRepository = bookRepository;
        this.borrowerRepository = borrowerRepository;
        this.finePolicyRepository = finePolicyRepository;
    }

    @Override
    public void run(String... args) {
        //log.info("Initializing sample data...");

        // Initialize Fine Policies
        initializeFinePolicies();

        // Initialize Books
        initializeBooks();

        // Initialize Borrowers
        initializeBorrowers();

       // log.info("Sample data initialization completed");
    }

    private void initializeFinePolicies() {
        if (finePolicyRepository.count() == 0) {
            finePolicyRepository.save(new FinePolicy(null, Book.BookCategory.FICTION, BigDecimal.valueOf(3.0)));
            finePolicyRepository.save(new FinePolicy(null, Book.BookCategory.TECH, BigDecimal.valueOf(5.0)));
            finePolicyRepository.save(new FinePolicy(null, Book.BookCategory.HISTORY, BigDecimal.valueOf(4.0)));
            finePolicyRepository.save(new FinePolicy(null, Book.BookCategory.SCIENCE, BigDecimal.valueOf(5.0)));
            finePolicyRepository.save(new FinePolicy(null, Book.BookCategory.BIOGRAPHY, BigDecimal.valueOf(4.0)));
            //log.info("Initialized fine policies");
        }
    }

    private void initializeBooks() {
        if (bookRepository.count() == 0) {
            // Fiction Books
            createBook("The Great Gatsby", "F. Scott Fitzgerald", Book.BookCategory.FICTION, 5);
            createBook("1984", "George Orwell", Book.BookCategory.FICTION, 4);
            createBook("To Kill a Mockingbird", "Harper Lee", Book.BookCategory.FICTION, 3);
            createBook("Pride and Prejudice", "Jane Austen", Book.BookCategory.FICTION, 3);

            // Tech Books
            createBook("Clean Code", "Robert C. Martin", Book.BookCategory.TECH, 6);
            createBook("Design Patterns", "Gang of Four", Book.BookCategory.TECH, 4);
            createBook("The Pragmatic Programmer", "Andrew Hunt", Book.BookCategory.TECH, 5);
            createBook("Effective Java", "Joshua Bloch", Book.BookCategory.TECH, 3);

            // History Books
            createBook("Sapiens", "Yuval Noah Harari", Book.BookCategory.HISTORY, 4);
            createBook("A Brief History of Time", "Stephen Hawking", Book.BookCategory.SCIENCE, 3);
            createBook("The Innovators", "Walter Isaacson", Book.BookCategory.BIOGRAPHY, 2);

           // log.info("Initialized {} books", bookRepository.count());
        }
    }

    private void createBook(String title, String author, Book.BookCategory category, int copies) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setCategory(category);
        book.setTotalCopies(copies);
        book.setAvailableCopies(copies);
        book.setAvailable(true);
        book.setDeleted(false);
        bookRepository.save(book);
    }

    private void initializeBorrowers() {
        if (borrowerRepository.count() == 0) {
            createBorrower("John Doe", "john.doe@email.com", Borrower.MembershipType.BASIC);
            createBorrower("Jane Smith", "jane.smith@email.com", Borrower.MembershipType.PREMIUM);
            createBorrower("Bob Johnson", "bob.johnson@email.com", Borrower.MembershipType.BASIC);
            createBorrower("Alice Williams", "alice.williams@email.com", Borrower.MembershipType.PREMIUM);
            createBorrower("Charlie Brown", "charlie.brown@email.com", Borrower.MembershipType.BASIC);

           // log.info("Initialized {} borrowers", borrowerRepository.count());
        }
    }

    private void createBorrower(String name, String email, Borrower.MembershipType type) {
        Borrower borrower = new Borrower();
        borrower.setName(name);
        borrower.setEmail(email);
        borrower.setMembershipType(type);
        borrower.setMaxBorrowLimit();
        borrowerRepository.save(borrower);
    }
}
