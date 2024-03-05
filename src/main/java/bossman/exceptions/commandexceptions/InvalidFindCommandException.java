package bossman.exceptions.commandexceptions;

import bossman.exceptions.BossManExceptions;

/**
 * InvalidFindCommandException is thrown when there is an error related to a find command.
 * It is a subclass of BossManExceptions.
 */
public class InvalidFindCommandException extends BossManExceptions {

    /**
     * Constructs an InvalidFindCommandException with the specified detail message.
     * Prepends "Find error: " to the message for clarity.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public InvalidFindCommandException(String message) {
        super("Find error: " + message);
    }
}
