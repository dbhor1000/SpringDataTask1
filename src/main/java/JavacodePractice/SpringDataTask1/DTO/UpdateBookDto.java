package JavacodePractice.SpringDataTask1.DTO;

import java.time.LocalDate;
import java.util.Objects;

public class UpdateBookDto {

    private String oldTitle;
    private String oldAuthor;
    private LocalDate oldPublicationYear;
    private String newTitle;
    private String newAuthor;
    private LocalDate newPublicationYear;

    public UpdateBookDto(String newAuthor, LocalDate newPublicationYear, String newTitle, String oldAuthor, LocalDate oldPublicationYear, String oldTitle) {
        this.newAuthor = newAuthor;
        this.newPublicationYear = newPublicationYear;
        this.newTitle = newTitle;
        this.oldAuthor = oldAuthor;
        this.oldPublicationYear = oldPublicationYear;
        this.oldTitle = oldTitle;
    }

    public String getNewAuthor() {
        return newAuthor;
    }

    public void setNewAuthor(String newAuthor) {
        this.newAuthor = newAuthor;
    }

    public LocalDate getNewPublicationYear() {
        return newPublicationYear;
    }

    public void setNewPublicationYear(LocalDate newPublicationYear) {
        this.newPublicationYear = newPublicationYear;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }

    public String getOldAuthor() {
        return oldAuthor;
    }

    public void setOldAuthor(String oldAuthor) {
        this.oldAuthor = oldAuthor;
    }

    public LocalDate getOldPublicationYear() {
        return oldPublicationYear;
    }

    public void setOldPublicationYear(LocalDate oldPublicationYear) {
        this.oldPublicationYear = oldPublicationYear;
    }

    public String getOldTitle() {
        return oldTitle;
    }

    public void setOldTitle(String oldTitle) {
        this.oldTitle = oldTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateBookDto that = (UpdateBookDto) o;
        return Objects.equals(oldTitle, that.oldTitle) && Objects.equals(oldAuthor, that.oldAuthor) && Objects.equals(oldPublicationYear, that.oldPublicationYear) && Objects.equals(newTitle, that.newTitle) && Objects.equals(newAuthor, that.newAuthor) && Objects.equals(newPublicationYear, that.newPublicationYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oldTitle, oldAuthor, oldPublicationYear, newTitle, newAuthor, newPublicationYear);
    }
}
