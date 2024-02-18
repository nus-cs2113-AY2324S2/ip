package yuki.exceptions;

public class YukiExceptions {
    public static class InvalidDescriptionException extends Exception {
        public InvalidDescriptionException(String message) {
            super(message);
        }
    }

    public static class InvalidIndexException extends Exception {
        public InvalidIndexException(String message) {
            super(message);
        }
    }
}
