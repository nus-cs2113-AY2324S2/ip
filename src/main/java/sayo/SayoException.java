package sayo;

/**
 * Represents an exception specific to the Sayo application.
 * This class extends the {@code Exception} class and is used to handle
 * application-specific exceptions that occur during the operation of Sayo.
 */
public class SayoException extends Exception {
    /**
     * Constructs a new {@code SayoException} with the specified detail message.
     * @param message The detail message which provides more information about the exception.
     */
    public SayoException(String message) {
        super(message);
    }
}
