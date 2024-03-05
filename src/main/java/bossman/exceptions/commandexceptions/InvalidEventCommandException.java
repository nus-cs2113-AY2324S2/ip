package bossman.exceptions.commandexceptions;

import bossman.exceptions.BossManExceptions;

/**
 * InvalidEventCommandException is thrown when there is an error related to an event command.
 * It is a subclass of BossManExceptions.
 */
public class InvalidEventCommandException extends BossManExceptions {

    /**
     * Constructs an InvalidEventCommandException with the specified detail message.
     * Prepends "Event error: " to the message for clarity.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public InvalidEventCommandException(String message) {
        super("Event error: " + message);
    }
}
