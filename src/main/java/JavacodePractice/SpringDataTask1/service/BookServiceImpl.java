package JavacodePractice.SpringDataTask1.service;

import JavacodePractice.SpringDataTask1.DTO.BookDTO;
import JavacodePractice.SpringDataTask1.DTO.UpdateBookDto;
import JavacodePractice.SpringDataTask1.Excepion.BookAlreadyExistsException;
import JavacodePractice.SpringDataTask1.Excepion.BookDoesNotExistException;
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
                throw new BookAlreadyExistsException("Book with provided details already exists.");
            }

            BookEntity book = new BookEntity(bookDTO.getAuthor(), bookDTO.getPublicationYear(), bookDTO.getTitle());
            bookRepository.createBook(book);
            BookEntity bookCreated = bookRepository.getBookByTitleAuthorAndPublicationYear(bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPublicationYear());
            return bookCreated;
        }

        @Transactional
        public BookEntity updateBook(UpdateBookDto updateBookDto) {

            BookEntity bookToUpdate = bookRepository.getBookByTitleAuthorAndPublicationYear(updateBookDto.getOldTitle(), updateBookDto.getOldAuthor(), updateBookDto.getOldPublicationYear());
            BookEntity checkIfBookInfoIsOccupied = bookRepository.getBookByTitleAuthorAndPublicationYear(updateBookDto.getNewTitle(), updateBookDto.getNewAuthor(), updateBookDto.getNewPublicationYear());

            if(bookToUpdate == null) {
                throw new BookDoesNotExistException("No book with provided details found to make an update.");
            }

            if(checkIfBookInfoIsOccupied != null) {
                throw new BookAlreadyExistsException("New details for book update have already been used by another book.");
            }

            bookToUpdate.setAuthor(updateBookDto.getNewAuthor());
            bookToUpdate.setTitle(updateBookDto.getNewTitle());
            bookToUpdate.setPublicationYear(updateBookDto.getNewPublicationYear());

            bookRepository.updateBook(bookToUpdate);
            return bookToUpdate;
        }

        @Transactional
        public void deleteBook(BookDTO bookDTO) {

            if(bookRepository.getBookByTitleAuthorAndPublicationYear(bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPublicationYear()) == null){
                throw new BookDoesNotExistException("Book to delete was not found.");
            }
            bookRepository.deleteBookByTitleAuthorAndPublicationYear(bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPublicationYear());

        }

    }

