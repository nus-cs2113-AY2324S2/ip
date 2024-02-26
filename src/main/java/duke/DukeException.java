package duke;

/**
 * Represents an exception for the Duke chatbot.
 */
public class DukeException extends Exception {
    /**
     * Constructs a new DukeException object with the error message.
     *
     * @param message The error message of the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
