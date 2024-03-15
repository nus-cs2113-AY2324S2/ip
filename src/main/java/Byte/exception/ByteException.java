package Byte.exception;

/**
 * Exception class for Byte chatbot.
 * Represents exceptions specific to the Byte chatbot application.
 */
public class ByteException extends Exception {

    /**
     * Constructs a new ByteException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     */
    public ByteException(String message) {
        super(message);
    }
}
