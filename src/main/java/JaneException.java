/**
 * Customs exception that extends the base Exception class.
 * It is used to handle exceptions specific to the Jane chatbot.
 */
public class JaneException extends Exception {
    /**
     * Constructs a JaneException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public JaneException(String message) {
        super(message);
    }
}