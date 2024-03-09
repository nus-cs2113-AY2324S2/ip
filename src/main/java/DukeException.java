/**
 * Represents exceptions specific to the Duke application.
 * This class extended the standard {@code Exception} class and is used for handing custom
 * errors in the application.
 */
public class DukeException extends Exception {
    /**
     * Constructs the new DukeException with the specified details message.
     * @param message The detail message.
     */
    public DukeException(String message) {
        super(message);
    }
}
