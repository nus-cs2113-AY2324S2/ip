package exceptions.descriptions;

import exceptions.WallybotException;

/**
 * Represents the exception thrown when the description for a task to add is incomplete.
 */
public class IncompleteDescriptionException extends WallybotException {
    /**
     * Constructs IncompleteDescriptionException.
     */
    public IncompleteDescriptionException() {
        message = "Oops, the description is incomplete :O";
    }
}
