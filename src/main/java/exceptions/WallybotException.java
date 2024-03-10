package exceptions;

/**
 * Represents abstract superclass for Wallybot-specific exceptions.
 */
public abstract class WallybotException extends Exception {
    protected String message;

    /**
     * Constructs WallybotException.
     */
    public WallybotException() {}

    /**
     * Returns error message.
     */
    public String getMessage() {
        return message;
    }
}
