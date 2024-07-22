package JavacodePractice.SpringDataTask1.repository;

import JavacodePractice.SpringDataTask1.model.BookEntity;
import java.time.LocalDate;
import java.util.Optional;

public interface BookRepository {

    // 1. Create a new book
    public void createBook(BookEntity book);
    // 2. Get/read a book by title+author+publicationYear
    public Optional<BookEntity> getBookByTitleAuthorAndPublicationYear(String title, String author, LocalDate publicationYear);
    // 3. Update a book (title/author/publicationYear)
    public void updateBook(BookEntity book);
    // 4. Delete a book by title/author/publicationYear
    public void deleteBookByTitleAuthorAndPublicationYear(String title, String author, LocalDate publicationYear);

}
