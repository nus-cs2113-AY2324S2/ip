/**
 * Represents a custom exception class designed for Jarvas to handle errors during command processing.
 */
public class CustomException extends RuntimeException {
    /**
     * Constructs a new CustomException with the specified error message.
     *
     * @param message The error message to be associated with this exception.
     */
    public CustomException(String message) {
        super("\n" + "Error Occurred: " + "\n" + message + "\n");
    }
}
