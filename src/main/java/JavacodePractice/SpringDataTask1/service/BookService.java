package JavacodePractice.SpringDataTask1.service;

import JavacodePractice.SpringDataTask1.DTO.BookDTO;
import JavacodePractice.SpringDataTask1.DTO.UpdateBookDto;
import JavacodePractice.SpringDataTask1.Excepion.BookAlreadyExistsException;
import JavacodePractice.SpringDataTask1.Excepion.BookDoesNotExistException;
import JavacodePractice.SpringDataTask1.model.BookEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BookService {

    public Optional<BookEntity> getBookByNameAndYearAndAuthor(BookDTO bookDTO);
    public Optional<BookEntity> createBook(BookDTO bookDTO);
    @Transactional
    public Optional<BookEntity> updateBook(UpdateBookDto updateBookDto);
    @Transactional
    public Long deleteBook(BookDTO bookDTO);

}
