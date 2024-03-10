package exceptions.descriptions;

import exceptions.WallybotException;

/**
 * Represents the exception thrown when the description for a task to add is empty.
 */
public class EmptyDescriptionException extends WallybotException {
    /**
     * Constructs EmptyDescriptionException.
     */
    public EmptyDescriptionException() {
        message = "Whoopsies, the description for a task cannot be empty :|";
    }
}
