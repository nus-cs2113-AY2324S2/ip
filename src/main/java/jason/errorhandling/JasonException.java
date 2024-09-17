package jason.errorhandling;

public class JasonException extends Exception {


    /**
     * Constructs a new {@code JasonException} with the specified detail message.
     * The message can be retrieved later through the {@link Throwable#getMessage()} method.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link Throwable#getMessage()} method.
     */
    public JasonException(String message) {
        super(message);
    }
}
