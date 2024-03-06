package Parser;

/**
 * Represents a collection of custom exception types for Duke.
 * These exceptions are used to handle specific errors in Duke's functionality.
 */
public class DukeExceptions {
    public static class InvalidItemException extends Exception {
    }

    public static class NoDescriptionException extends Exception{
    }

    public static void throwInvalidItemException() throws InvalidItemException {
        throw new InvalidItemException();
    }

    public static void throwNoDescriptionException() throws NoDescriptionException {
        throw new NoDescriptionException();
    }
}
