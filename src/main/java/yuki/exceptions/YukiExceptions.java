package yuki.exceptions;

/**
 * Custom exceptions used in the app.
 */
public class YukiExceptions {

    /**
     * Exception when the description is not valid.
     * E.g., give only one date for Event task.
     */
    public static class InvalidDescriptionException extends Exception {
        public InvalidDescriptionException(String message) {
            super(message);
        }
    }

    /**
     * Exception when the given index is not valid.
     * E.g., mark 3rd task when there are only two tasks.
     */
    public static class InvalidIndexException extends Exception {
        public InvalidIndexException(String message) {
            super(message);
        }
    }
}
