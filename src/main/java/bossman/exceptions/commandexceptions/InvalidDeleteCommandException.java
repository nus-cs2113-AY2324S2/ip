package bossman.exceptions.commandexceptions;

import bossman.exceptions.BossManExceptions;

/**
 * InvalidDeleteCommandException is thrown when there is an error related to a delete command.
 * It is a subclass of BossManExceptions.
 */
public class InvalidDeleteCommandException extends BossManExceptions {

    /**
     * Constructs an InvalidDeleteCommandException with the specified detail message.
     * Prepends "Delete error: " to the message for clarity.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public InvalidDeleteCommandException(String message) {
        super("Delete error: " + message);
    }
}
