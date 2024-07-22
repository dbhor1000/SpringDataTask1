package JavacodePractice.SpringDataTask1.controller;

import JavacodePractice.SpringDataTask1.DTO.BookDTO;
import JavacodePractice.SpringDataTask1.DTO.UpdateBookDto;
import JavacodePractice.SpringDataTask1.model.BookEntity;
import JavacodePractice.SpringDataTask1.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/book")
public class Controller {

    private final BookService bookService;

    public Controller(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<BookEntity> fetchOneBook(@RequestBody BookDTO bookDTO) {
        Optional<BookEntity> booksFound = bookService.getBookByNameAndYearAndAuthor(bookDTO);
        return booksFound.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookEntity> newBook(@RequestBody BookDTO bookDTO) {
        Optional<BookEntity> createdBook = bookService.createBook(bookDTO);
        return createdBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PatchMapping()
    public ResponseEntity<BookEntity> updateBook(@RequestBody UpdateBookDto updateBookDTO) {
        Optional<BookEntity> updatedBook = bookService.updateBook(updateBookDTO);
        return updatedBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping()
    public ResponseEntity<Long> deleteBook(@RequestBody BookDTO bookDTO) {

        Long bookDeleted = bookService.deleteBook(bookDTO);
        return ResponseEntity.ok(bookDeleted);
    }
}
