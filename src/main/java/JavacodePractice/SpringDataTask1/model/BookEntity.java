package JavacodePractice.SpringDataTask1.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("books")
public class BookEntity {

    @Id
    private Long id;
    private String title;
    private String author;
    private LocalDate publicationYear;

    public BookEntity() {
    }

    public BookEntity(String author, LocalDate publicationYear, String title) {
        this.author = author;
        this.publicationYear = publicationYear;
        this.title = title;
    }

    public BookEntity(String author, Long id, LocalDate publicationYear, String title) {
        this.author = author;
        this.id = id;
        this.publicationYear = publicationYear;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(LocalDate publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
