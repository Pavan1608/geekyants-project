package org.geekyants.service;

import org.geekyants.entity.Book;
import org.geekyants.entity.BookUpdateRequest;
import org.geekyants.exception.LibraryManagementException;
import org.geekyants.model.AvailabilitySummaryDTO;
import org.geekyants.model.BookDTO;
import org.geekyants.repository.BookRepository;
import org.geekyants.repository.BorrowRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BorrowRecordRepository borrowRecordRepository;
    private static final Logger log = Logger.getLogger(BookService.class.getName());

    @Autowired
    public BookService(BookRepository bookRepository, BorrowRecordRepository borrowRecordRepository) {
        this.bookRepository = bookRepository;
        this.borrowRecordRepository = borrowRecordRepository;
    }

    @Transactional
   // @CacheEvict(value = "books", allEntries = true)
    public BookDTO addBook(BookDTO request) {
        log.info("Adding book: " + request.getTitle());

        // Check if book already exists
        var existingBook = bookRepository.findByTitleAndAuthor(
                request.getTitle(), request.getAuthor());

        if (null!=existingBook) {
            Book book = existingBook;
            book.setTotalCopies(book.getTotalCopies() + request.getTotalCopies());
            book.setAvailableCopies(book.getAvailableCopies() + request.getTotalCopies());
            book.setAvailable(true);
            Book savedBook = bookRepository.save(book);
           log.info("Increased copies for existing book: " +savedBook.getId());
            return toResponse(savedBook);
        }

        // Create new book
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setCategory(request.getCategory());
        book.setTotalCopies(request.getTotalCopies());
        book.setAvailableCopies(request.getTotalCopies());
        book.setAvailable(true);

        Book savedBook = bookRepository.save(book);
        log.info("Created new book with ID: {}" + savedBook.getId());
        return toResponse(savedBook);
    }
    private BookDTO toResponse(Book book) {
        BookDTO bookDTO = new BookDTO();
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getAvailable(),
                book.getTotalCopies(),
                book.getAvailableCopies()
        );
   }
    public  BookDTO getBook(UUID bookId)
    {
  return  bookRepository.findById(bookId).map(this::toResponse).orElse(null);

    }

    public List<BookDTO> getBooks(Book.BookCategory category, Boolean available) {
        List<Book> bookList= bookRepository.findAllByOptionalCategoryAndAvailability(category, available);
        return Optional.of(bookList)
                .stream()
                .flatMap(List::stream)
                .map(this::toResponse)
                .toList();
    }

    public void deleteBook(UUID id) {
         bookRepository.deleteById(id);
    }

    public List<BookDTO> getSimilarBooks(UUID id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new LibraryManagementException("Book not found with ID: " + id));
        List<Book> similarBooks = bookRepository.findByCategoryOrAuthor(
                book.getCategory(), book.getAuthor());
        return similarBooks.stream()
                .filter(b -> !b.getId().equals(id))
                .map(this::toResponse)
                .toList();
    }

    public List<AvailabilitySummaryDTO> getAvailabilitySummary() {
        return null;
    }

    public BookDTO updateBook(UUID id, BookUpdateRequest bookUpdateRequest) {
       Book book= bookRepository.findById(id)
                .orElseThrow(()-> new LibraryManagementException("Book not found with ID: " + id));
       book.setAuthor(bookUpdateRequest.getAuthor());
       book.setAvailable(bookUpdateRequest.getAvailableCopies()!= null && bookUpdateRequest.getAvailableCopies()>1);
       book.setAvailableCopies(bookUpdateRequest.getAvailableCopies());
       book.setTitle(bookUpdateRequest.getTitle());
       book.setTotalCopies(bookUpdateRequest.getTotalCopies());

       return Optional.of(bookRepository.save(book)).map(this::toResponse).orElse(null);

    }


}
