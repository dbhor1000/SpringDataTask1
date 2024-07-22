package JavacodePractice.SpringDataTask1.ControllerTests;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.Optional;

import JavacodePractice.SpringDataTask1.DTO.BookDTO;
import JavacodePractice.SpringDataTask1.DTO.UpdateBookDto;
import JavacodePractice.SpringDataTask1.controller.Controller;
import JavacodePractice.SpringDataTask1.model.BookEntity;
import JavacodePractice.SpringDataTask1.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(Controller.class)
public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private BookDTO bookDTO;
    private BookEntity bookEntity;
    private BookEntity bookEntityUpdated;
    private UpdateBookDto updateBookDto;

    @BeforeEach
    public void setUp() {
        bookDTO = new BookDTO("Author", LocalDate.of(2020, 1, 1), "Title");
        bookEntity = new BookEntity("Author", 1L, LocalDate.of(2020, 1, 1), "Title");
        bookEntityUpdated = new BookEntity("NewAuthor", 1L, LocalDate.of(2021, 1, 1), "NewTitle");
        updateBookDto = new UpdateBookDto("NewAuthor", LocalDate.of(2021, 1, 1), "NewTitle", "OldAuthor", LocalDate.of(2020, 1, 1), "OldTitle");
    }

    @Test
    public void testFetchOneBook() throws Exception {
        when(bookService.getBookByNameAndYearAndAuthor(any())).thenReturn(Optional.of(bookEntity));

        mockMvc.perform(get("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(bookDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Title"))
                .andExpect(jsonPath("$.author").value("Author"))
                .andExpect(jsonPath("$.publicationYear").value("2020-01-01"));
    }

    @Test
    public void testNewBook() throws Exception {
        when(bookService.createBook(any())).thenReturn(Optional.of(bookEntity));

        mockMvc.perform(post("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(bookDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Title"))
                .andExpect(jsonPath("$.author").value("Author"))
                .andExpect(jsonPath("$.publicationYear").value("2020-01-01"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        when(bookService.updateBook(any())).thenReturn(Optional.of(bookEntityUpdated));

        mockMvc.perform(patch("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(updateBookDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("NewTitle"))
                .andExpect(jsonPath("$.author").value("NewAuthor"))
                .andExpect(jsonPath("$.publicationYear").value("2021-01-01"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        when(bookService.deleteBook(any())).thenReturn(1L);

        mockMvc.perform(delete("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(bookDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }
}