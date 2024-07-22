package JavacodePractice.SpringDataTask1.ServiceTests;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Optional;

import JavacodePractice.SpringDataTask1.DTO.BookDTO;
import JavacodePractice.SpringDataTask1.DTO.UpdateBookDto;
import JavacodePractice.SpringDataTask1.model.BookEntity;
import JavacodePractice.SpringDataTask1.repository.BookRepository;
import JavacodePractice.SpringDataTask1.service.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ServiceTests {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private BookDTO bookDTO;
    private BookEntity bookEntity;
    private UpdateBookDto updateBookDto;

    @BeforeEach
    public void setUp() {
        bookDTO = new BookDTO("Author", LocalDate.of(2020, 1, 1), "Title");
        bookEntity = new BookEntity("Author", 1L, LocalDate.of(2020, 1, 1), "Title");
        updateBookDto = new UpdateBookDto("NewAuthor", LocalDate.of(2021, 1, 1), "NewTitle", "OldAuthor", LocalDate.of(2020, 1, 1), "OldTitle");
    }

    @Test
    public void testGetBookByNameAndYearAndAuthor() {
        when(bookRepository.getBookByTitleAuthorAndPublicationYear("Title", "Author", LocalDate.of(2020, 1, 1))).thenReturn(Optional.of(bookEntity));

        Optional<BookEntity> result = bookService.getBookByNameAndYearAndAuthor(bookDTO);

        assertTrue(result.isPresent());
        assertEquals(bookEntity, result.get());
    }

    @Test
    public void testCreateBook() {
        when(bookRepository.getBookByTitleAuthorAndPublicationYear("Title", "Author", LocalDate.of(2020, 1, 1))).thenReturn(Optional.empty())
                .thenReturn(Optional.of(bookEntity));

        Optional<BookEntity> result = bookService.createBook(bookDTO);

        assertTrue(result.isPresent());
        assertEquals(bookEntity, result.get());
    }

    @Test
    public void testUpdateBook() {
        when(bookRepository.getBookByTitleAuthorAndPublicationYear("OldTitle", "OldAuthor", LocalDate.of(2020, 1, 1))).thenReturn(Optional.of(bookEntity));
        when(bookRepository.getBookByTitleAuthorAndPublicationYear("NewTitle", "NewAuthor", LocalDate.of(2021, 1, 1))).thenReturn(Optional.empty());

        Optional<BookEntity> result = bookService.updateBook(updateBookDto);

        assertTrue(result.isPresent());
        assertEquals(bookEntity, result.get());
        assertEquals("NewTitle", bookEntity.getTitle());
        assertEquals("NewAuthor", bookEntity.getAuthor());
        assertEquals(LocalDate.of(2021, 1, 1), bookEntity.getPublicationYear());
    }

    @Test
    public void testDeleteBook() {
        when(bookRepository.getBookByTitleAuthorAndPublicationYear("Title", "Author", LocalDate.of(2020, 1, 1))).thenReturn(Optional.of(bookEntity));

        Long result = bookService.deleteBook(bookDTO);

        assertEquals(1L, result);
    }
}