package Exceptions;

/**
 * The LoadFileException class represents an exception that is thrown
 * when there is an error loading a file.
 */

public class LoadFileException extends Exception {

    /**
     * Constructs a new LoadFileException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */

    public LoadFileException(String message) {
        super(message);
    }
}
