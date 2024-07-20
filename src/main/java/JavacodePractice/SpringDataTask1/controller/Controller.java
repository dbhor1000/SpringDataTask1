package JavacodePractice.SpringDataTask1.controller;

import JavacodePractice.SpringDataTask1.DTO.BookDTO;
import JavacodePractice.SpringDataTask1.DTO.UpdateBookDto;
import JavacodePractice.SpringDataTask1.model.BookEntity;
import JavacodePractice.SpringDataTask1.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class Controller {

    private final BookService bookService;

    public Controller(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<?> fetchOneBook(@RequestBody BookDTO bookDTO) {

        BookEntity booksFound = bookService.getBookByNameAndYearAndAuthor(bookDTO);
        if(booksFound == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(booksFound);
    }

    @PostMapping
    public ResponseEntity<BookEntity> newBook(@RequestBody BookDTO bookDTO) {

        BookEntity createdBook = bookService.createBook(bookDTO);

        if(createdBook == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(createdBook);
    }

    @PatchMapping()
    public ResponseEntity<BookEntity> updateBook(@RequestBody UpdateBookDto updateBookDTO) {

        BookEntity updatedBook = bookService.updateBook(updateBookDTO);

        if(updatedBook == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteBook(@RequestBody BookDTO bookDTO) {

        if (bookService.deleteBook(bookDTO)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}