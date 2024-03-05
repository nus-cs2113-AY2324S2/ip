package bossman.exceptions.commandexceptions;

import bossman.exceptions.BossManExceptions;

/**
 * InvalidDeadlineCommandException is thrown when there is an error related to a deadline command.
 * It is a subclass of BossManExceptions.
 */
public class InvalidDeadlineCommandException extends BossManExceptions {

    /**
     * Constructs an InvalidDeadlineCommandException with the specified detail message.
     * Prepends "Deadline error: " to the message for clarity.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public InvalidDeadlineCommandException(String message) {
        super("Deadline error: " + message);
    }
}
