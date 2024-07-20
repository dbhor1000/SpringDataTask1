package JavacodePractice.SpringDataTask1.DTO;

import java.time.LocalDate;

public class UpdateBookDto {

    private String oldTitle;
    private String oldAuthor;
    private LocalDate oldPublicationYear;
    private String newTitle;
    private String newAuthor;
    private LocalDate newPublicationYear;

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
}
