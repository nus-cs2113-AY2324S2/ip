package suv.Command;

/**
 * The SuvException class represents an exception specific to the Suv application.
 * It extends the standard Java Exception class.
 */
public class SuvException extends Exception{
    public String warning;

    /**
     * Constructs a new SuvException object with the specified warning message.
     *
     * @param warning The warning message associated with the exception.
     */
    public SuvException(String warning) {
        this.warning = warning;
    }
}
