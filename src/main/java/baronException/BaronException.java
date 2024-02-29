package baronException;

/**
 * Representation of an exception unique to Baron.
 */

public class BaronException extends Exception {
    public BaronException (String message) {
        super(message);
    }
}
