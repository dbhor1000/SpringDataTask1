package JavacodePractice.SpringDataTask1.service;

import JavacodePractice.SpringDataTask1.DTO.BookDTO;
import JavacodePractice.SpringDataTask1.DTO.UpdateBookDto;
import JavacodePractice.SpringDataTask1.model.BookEntity;
import JavacodePractice.SpringDataTask1.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookEntity getBookByNameAndYearAndAuthor(BookDTO bookDTO) {
        BookEntity booksFounds = bookRepository.getBookByTitleAuthorAndPublicationYear(bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPublicationYear());

        return booksFounds;
    }

        public BookEntity createBook(BookDTO bookDTO) {
            if(bookRepository.getBookByTitleAuthorAndPublicationYear(bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPublicationYear()) != null){
                return null;
            }

            BookEntity book = new BookEntity(bookDTO.getAuthor(), bookDTO.getPublicationYear(), bookDTO.getTitle());
            bookRepository.createBook(book);
            BookEntity bookCreated = bookRepository.getBookByTitleAuthorAndPublicationYear(bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPublicationYear());
            return bookCreated;
        }

        @Transactional
        public BookEntity updateBook(UpdateBookDto updateBookDto) {

            BookEntity findBookToUpdate = bookRepository.getBookByTitleAuthorAndPublicationYear(updateBookDto.getOldTitle(), updateBookDto.getOldAuthor(), updateBookDto.getOldPublicationYear());
            BookEntity checkIfBookInfoIsOccupied = bookRepository.getBookByTitleAuthorAndPublicationYear(updateBookDto.getNewTitle(), updateBookDto.getNewAuthor(), updateBookDto.getNewPublicationYear());

            if(findBookToUpdate == null || checkIfBookInfoIsOccupied != null) {
                return null;
            }

            findBookToUpdate.setAuthor(updateBookDto.getNewAuthor());
            findBookToUpdate.setTitle(updateBookDto.getNewTitle());
            findBookToUpdate.setPublicationYear(updateBookDto.getNewPublicationYear());

            bookRepository.updateBook(findBookToUpdate);
            return findBookToUpdate;
        }

        @Transactional
        public boolean deleteBook(BookDTO bookDTO) {

            if(bookRepository.getBookByTitleAuthorAndPublicationYear(bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPublicationYear()) == null){
                return false;
            }
            bookRepository.deleteBookByTitleAuthorAndPublicationYear(bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPublicationYear());
            return true;

        }

    }

