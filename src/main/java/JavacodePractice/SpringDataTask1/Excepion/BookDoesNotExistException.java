package JavacodePractice.SpringDataTask1.Excepion;

public class BookDoesNotExistException extends RuntimeException {
    public BookDoesNotExistException(String message) {
        super(message);
    }
}