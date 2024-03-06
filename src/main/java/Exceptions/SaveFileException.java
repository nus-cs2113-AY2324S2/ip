package Exceptions;

/**
 * The SaveFileException class represents an exception that is thrown
 * when there is an error saving a file.
 */

public class SaveFileException extends Exception {

    public SaveFileException(String message) {
        super(message);
    }
}
