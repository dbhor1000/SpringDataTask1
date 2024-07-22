package JavacodePractice.SpringDataTask1.service;

import JavacodePractice.SpringDataTask1.DTO.BookDTO;
import JavacodePractice.SpringDataTask1.DTO.UpdateBookDto;
import JavacodePractice.SpringDataTask1.Excepion.BookAlreadyExistsException;
import JavacodePractice.SpringDataTask1.Excepion.BookDoesNotExistException;
import JavacodePractice.SpringDataTask1.model.BookEntity;
import JavacodePractice.SpringDataTask1.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Optional<BookEntity> getBookByNameAndYearAndAuthor(BookDTO bookDTO) {
        if (bookRepository.getBookByTitleAuthorAndPublicationYear(bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPublicationYear()).isEmpty()) {
            throw new BookDoesNotExistException("Book with provided details does not exist.");
        }
        return bookRepository.getBookByTitleAuthorAndPublicationYear(bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPublicationYear());
    }

    public Optional<BookEntity> createBook(BookDTO bookDTO) {

        Optional<BookEntity> bookFound = bookRepository.getBookByTitleAuthorAndPublicationYear(bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPublicationYear());

        if (bookFound.isPresent()) {
            throw new BookAlreadyExistsException("Book with provided details already exists.");
        }

        BookEntity book = new BookEntity(bookDTO.getAuthor(), bookDTO.getPublicationYear(), bookDTO.getTitle());
        bookRepository.createBook(book);
        return bookRepository.getBookByTitleAuthorAndPublicationYear(bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPublicationYear());
    }

    @Transactional
    public Optional<BookEntity> updateBook(UpdateBookDto updateBookDto) {
        Optional<BookEntity> bookToUpdateOpt = bookRepository.getBookByTitleAuthorAndPublicationYear(updateBookDto.getOldTitle(), updateBookDto.getOldAuthor(), updateBookDto.getOldPublicationYear());
        if (bookToUpdateOpt.isEmpty()) {
            throw new BookDoesNotExistException("No book with provided details found to make an update.");
        }

        Optional<BookEntity> checkIfBookInfoIsOccupied = bookRepository.getBookByTitleAuthorAndPublicationYear(updateBookDto.getNewTitle(), updateBookDto.getNewAuthor(), updateBookDto.getNewPublicationYear());
        if (checkIfBookInfoIsOccupied.isPresent()) {
            throw new BookAlreadyExistsException("New details for book update have already been used by another book.");
        }

        BookEntity bookToUpdate = bookToUpdateOpt.get();
        bookToUpdate.setAuthor(updateBookDto.getNewAuthor());
        bookToUpdate.setTitle(updateBookDto.getNewTitle());
        bookToUpdate.setPublicationYear(updateBookDto.getNewPublicationYear());

        bookRepository.updateBook(bookToUpdate);
        return Optional.of(bookToUpdate);
    }

        @Transactional
        public Long deleteBook(BookDTO bookDTO) {

        Optional<BookEntity> bookToDelete = bookRepository.getBookByTitleAuthorAndPublicationYear(bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPublicationYear());
            if(bookToDelete.isEmpty()){
                throw new BookDoesNotExistException("Book to delete was not found.");
            }
            bookRepository.deleteBookByTitleAuthorAndPublicationYear(bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPublicationYear());

            return bookToDelete.get().getId();
        }
    }

