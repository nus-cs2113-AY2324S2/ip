package bossman.exceptions.commandexceptions;

import bossman.exceptions.BossManExceptions;

/**
 * InvalidTodoCommandException is thrown when there is an error related to a todo command.
 * It is a subclass of BossManExceptions.
 */
public class InvalidTodoCommandException extends BossManExceptions {

    /**
     * Constructs an InvalidTodoCommandException with the specified detail message.
     * Prepends "Todo error: " to the message for clarity.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public InvalidTodoCommandException(String message) {
        super("Todo error: " + message);
    }
}
