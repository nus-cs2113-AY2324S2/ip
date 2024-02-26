package mimi.exceptions;

public class MimiException {

    // Error messages
    public final static String INCORRECT_INSTRUCTION_MSG = "unknown instruction. " +
            "Type of instructions available [deadline/todo/event/list/bye]";
    public final static String INCORRECT_TODO_FORMAT_MSG = "todo format is invalid. " +
            "Proper syntax: todo [task]";
    public final static String INCORRECT_DEADLINE_FORMAT_MSG = "deadline format is invalid. " +
            "Proper syntax: deadline [instruction] /by [deadline]";
    public final static String INCORRECT_EVENT_FORMAT_MSG = "event format is invalid. " +
            "Proper syntax: event [eventName] /from [startDate] /to [endDate]";

    public final static String INCORRECT_INDEX_FORMAT_MSG = "delete/mark/unmark format is invalid. " +
            "Proper syntax: delete/mark/unmark [task number]";
    public final static String INSUFFICIENT_TODO_PARAMETERS_MSG = "todo parameters is incomplete. " +
            "Proper syntax: todo [task number]";
    public final static String INSUFFICIENT_DEADLINE_PARAMETERS_MSG = "deadline parameters is incomplete. " +
            "Proper syntax: deadline [instruction] /by [deadline]";
    public final static String INSUFFICIENT_EVENT_PARAMETERS_MSG = "event parameters is incomplete. " +
            "Proper syntax: event [eventName] /from [startDate] /to [endDate]";
    public final static String INSUFFICIENT_INDEX_PARAMETERS_MSG = "delete/mark/unmark parameters is incomplete. " +
            "Proper syntax: delete/mark/unmark [task number]";
    public final static String TASK_NOT_FOUND_MSG = "task not found. " + "Please check the task number again.";

    public final static String FILE_CORRUPTED_MSG = "file is corrupted. " + "Please check the file again.";

    public final static String LOAD_ERROR_MSG = "Unable to load file. Please ensure /data/mimi.logs exists.";

    public static class InsufficientParameters extends Exception {
        public InsufficientParameters(String message) {
            super("\u001B[31mError: " + message + "\u001B[0m");
        }
    }

    public static class IncorrectFormat extends Exception {
        public IncorrectFormat(String message) {
            super("\u001B[31mError: " + message + "\u001B[0m");
        }
    }

    public static class TaskNotFound extends Exception {
        public TaskNotFound(String message) {
            super("\u001B[31mError: " + message + "\u001B[0m");
        }
    }

    public static class FileCorrupted extends Exception {
        public FileCorrupted(String message) {
            super("\u001B[31mError: " + message + "\u001B[0m");
        }
    }

    public static class LoadError extends Exception {
        public LoadError(String message) {
            super("\u001B[31mError: " + message + "\u001B[0m");
        }

    }
}
