package bossman.exceptions.commandexceptions;

import bossman.exceptions.BossManExceptions;

/**
 * UnknownCommandException is thrown when the user input does not correspond to any known command.
 * It is a subclass of BossManExceptions.
 */
public class UnknownCommandException extends BossManExceptions {

    /**
     * Constructs an UnknownCommandException with the specified detail message.
     * Prepends "Error: " to the message for clarity.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public UnknownCommandException(String message) {
        super("Error: " + message);
    }
}
