package artemis.errors;

public class Errors {
    public static class InvalidTodoException extends Exception {
    }

    public static class InvalidDeadlineException extends Exception {
    }

    public static class InvalidEventException extends Exception {
    }

    public static class InvalidMarkUnmarkException extends Exception {
    }

    public static class InvalidMarkUnmarkIndexException extends Exception {
    }

    public static class CorruptedSaveException extends Exception {
    }
}
