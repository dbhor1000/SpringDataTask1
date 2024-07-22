package JavacodePractice.SpringDataTask1.service;

import JavacodePractice.SpringDataTask1.DTO.BookDTO;
import JavacodePractice.SpringDataTask1.DTO.UpdateBookDto;
import JavacodePractice.SpringDataTask1.model.BookEntity;
import org.springframework.transaction.annotation.Transactional;

public interface BookService {

    public BookEntity getBookByNameAndYearAndAuthor(BookDTO bookDTO);
    public BookEntity createBook(BookDTO bookDTO);
    @Transactional
    public BookEntity updateBook(UpdateBookDto updateBookDto);
    @Transactional
    public void deleteBook(BookDTO bookDTO);

}
