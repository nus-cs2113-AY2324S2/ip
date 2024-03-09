package exceptions;

/**
 * The InvalidTodoFormatException class represents an exception that is thrown
 * when the todo format provided by the user is invalid.
 */

public class InvalidTodoFormatException extends Exception {

    public InvalidTodoFormatException(String message) {
        super(message);
    }
}
