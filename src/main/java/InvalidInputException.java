/**
 * The InvalidInputException class represents an exception that is thrown when the input provided is invalid.
 */
public class InvalidInputException extends Exception {
    /**
     * Constructs an InvalidInputException with the specified error message.
     *
     * @param errorMessage The error message describing the nature of the invalid input.
     */
    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }
}
