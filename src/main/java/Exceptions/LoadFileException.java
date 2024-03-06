package Exceptions;

/**
 * The LoadFileException class represents an exception that is thrown
 * when there is an error loading a file.
 */

public class LoadFileException extends Exception {

    public LoadFileException(String message) {
        super(message);
    }
}
