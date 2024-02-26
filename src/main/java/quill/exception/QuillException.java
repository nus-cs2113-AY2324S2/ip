package quill.exception;

/**
 * The QuillException Class represents custom
 * exceptions specific to the Quill application.
 * It extends the Exception class.
 */
public class QuillException extends Exception{

    /**
     * Constructs a new QuillException with a specified error message.
     *
     * @param message The error message.
     */
    public QuillException(String message) {
        super(message);
    }

    /**
     * Constructs a new QuillException.
     */
    public QuillException() {
    }
}
