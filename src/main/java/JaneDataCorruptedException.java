/**
 * Customs exception that extends the base Exception class.
 * It is used to handle exceptions related to data corruption in the Jane task management application.
 */
public class JaneDataCorruptedException extends Exception{
    /**
     * Constructs a JaneDataCorruptedException with the specified error message.
     *
     * @param message The error message associated with the data corruption exception.
     */
    public JaneDataCorruptedException(String message) {
        super(message);
    }
}