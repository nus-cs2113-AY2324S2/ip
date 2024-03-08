package exception;

/**
 * The ZukeException class represents an exception that is thrown when an error occurs in ZukeBot.
 */
public class ZukeException extends Exception {
    public ZukeException(String errorMessage) {
        super(errorMessage);
    }
}
