package Exceptions;

/**
 * The InvalidTodoFormatException class represents an exception that is thrown
 * when the todo format provided by the user is invalid.
 */

public class InvalidTodoFormatException extends Exception {

    /**
     * Constructs a new InvalidTodoFormatException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */

    public InvalidTodoFormatException(String message) {
        super(message);
    }
}
