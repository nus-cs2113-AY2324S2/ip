package yuki.exceptions;

public class YukiExceptions {
    public static class InvalidDescriptionException extends Exception {}

    public static class InvalidIndexException extends Exception {

        public String message;

        public InvalidIndexException(String message) {
            super(message);
            this.message = message;
        }

        @Override
        public String toString() {
            return message + "\nPlease enter a valid index\n";
        }

    }

}
