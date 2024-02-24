package bossman.exceptions.commandexceptions;

import bossman.exceptions.BossManExceptions;

/**
 * InvalidMarkCommandException is thrown when there is an error related to a mark or unmark command.
 * It is a subclass of BossManExceptions.
 */
public class InvalidMarkCommandException extends BossManExceptions {

    /**
     * Constructs an InvalidMarkCommandException with the specified detail message.
     * Prepends "Mark/ unmark error: " to the message for clarity.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public InvalidMarkCommandException(String message) {
        super("Mark/ unmark error: " + message);
    }
}
