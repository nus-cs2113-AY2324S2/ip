/**
 * DukeException class represents an exception specific to the Duke application.
 * It extends the standard Exception class.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException class.
     *
     * @param message The detailed error message for the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
