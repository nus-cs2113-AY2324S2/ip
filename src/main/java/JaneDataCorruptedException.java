/**
 * Customs exception that extends the base Exception class.
 * It is used to handle exceptions related to data corruption in the Jane task management application.
 */
public class JaneDataCorruptedException extends Exception{
    public JaneDataCorruptedException(String message) {
        super(message);
    }
}