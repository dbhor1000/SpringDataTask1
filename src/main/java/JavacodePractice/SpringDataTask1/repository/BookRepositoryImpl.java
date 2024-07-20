package JavacodePractice.SpringDataTask1.repository;

import JavacodePractice.SpringDataTask1.model.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 1. Create a new book
    public void createBook(BookEntity book) {
        jdbcTemplate.update(
                "INSERT INTO books (\"title\", \"author\", \"publicationYear\") VALUES (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getPublicationYear()
        );
    }

    // 2. Get/read a book by title+author+publicationYear
    public BookEntity getBookByTitleAuthorAndPublicationYear(String title, String author, LocalDate publicationYear) {
        List<BookEntity> books = jdbcTemplate.query(
                "SELECT * FROM books WHERE \"title\" = ? AND \"author\" = ? AND \"publicationYear\" = ?",
                new Object[]{title, author, publicationYear},
                new BookRowMapper()
        );
        return books.isEmpty() ? null : books.get(0);
    }

    // 3. Update a book (title/author/publicationYear)
    public void updateBook(BookEntity book) {
        jdbcTemplate.update(
                "UPDATE books SET \"title\" = ?, \"author\" = ?, \"publicationYear\" = ? WHERE \"id\" = ?",
                book.getTitle(), book.getAuthor(), book.getPublicationYear(), book.getId()
        );
    }

    // 4. Delete a book by title/author/publicationYear
    public void deleteBookByTitleAuthorAndPublicationYear(String title, String author, LocalDate publicationYear) {
        jdbcTemplate.update(
                "DELETE FROM books WHERE \"title\" = ? AND \"author\" = ? AND \"publicationYear\" = ?",
                title, author, publicationYear
        );
    }

    private static class BookRowMapper implements RowMapper<BookEntity> {
        @Override
        public BookEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            BookEntity book = new BookEntity();
            book.setId(rs.getLong("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setPublicationYear(rs.getDate("publicationYear").toLocalDate());
            return book;
        }
    }
}
