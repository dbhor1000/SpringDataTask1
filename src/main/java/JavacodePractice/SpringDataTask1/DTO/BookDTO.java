package JavacodePractice.SpringDataTask1.DTO;

import java.time.LocalDate;
import java.util.Objects;

public class BookDTO {

    private String title;
    private String author;
    private LocalDate publicationYear;

    public BookDTO(String author, LocalDate publicationYear, String title) {
        this.author = author;
        this.publicationYear = publicationYear;
        this.title = title;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return Objects.equals(title, bookDTO.title) && Objects.equals(author, bookDTO.author) && Objects.equals(publicationYear, bookDTO.publicationYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, publicationYear);
    }
}
