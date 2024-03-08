/**
 * Exception thrown when an error occurs in Duke application.
 *
 * DukeException is thrown to indicate that an error has occurred during the execution
 * of the Duke application. It contains the error message that provides information about
 * the nature of the error.
 
 * @param message the error message describing the cause of the exception
 * @return a DukeException object with the specified error message
 * @throws DukeException if there is an error while creating the DukeException object
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
