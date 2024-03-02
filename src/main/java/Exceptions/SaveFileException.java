package Exceptions;

/**
 * The SaveFileException class represents an exception that is thrown
 * when there is an error saving a file.
 */

public class SaveFileException extends Exception {

    /**
     * Constructs a new SaveFileException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */

    public SaveFileException(String message) {
        super(message);
    }
}
