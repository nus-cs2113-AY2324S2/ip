package exceptions;

/**
 * Represents an exception thrown when the input is unknown.
 */
public class UnknownInputException extends Exception {

    /**
     * Retrieves the error message for this exception.
     *
     * @return The error message indicating that the input is unknown
     */
    @Override
    public String getMessage() {
        return "\t I've not seen this input before. Please tell me something else I can help you with." +
                "Try the help command to see the list of available inputs!";
    }
}
