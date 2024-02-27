package exceptions;

/**
 * Exception thrown when the user input does not match any of the available commands
 */
public class InvalidInputException extends Exception {

    /**
     * Constructor for this exception with an error message indicating that the input is invalid
     */
    public InvalidInputException() {
        super("ERROR: Invalid input!");
    }
}
