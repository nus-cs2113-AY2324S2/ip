package exceptions;

public class MimiException {

    // Error messages
    public final static String INCORRECT_INSTRUCTION = "unknown instruction. " +
            "Type of instructions available [deadline/todo/event/list/bye]";
    public final static String INCORRECT_TODO_FORMAT = "todo format is invalid. " +
            "Proper syntax: todo [task]";
    public final static String INCORRECT_DEADLINE_FORMAT = "deadline format is invalid. " +
            "Proper syntax: deadline [instruction] /by [deadline]";
    public final static String INCORRECT_EVENT_FORMAT = "event format is invalid. " +
            "Proper syntax: event [eventName] /from [startDate] /to [endDate]";

    public final static String INCORRECT_MARK_FORMAT = "mark/unmark format is invalid. " +
            "Proper syntax: mark/unmark [task number]";
    public final static String INSUFFICIENT_TODO_PARAMETERS = "todo parameters is incomplete. " +
            "Proper syntax: todo [task]";
    public final static String INSUFFICIENT_DEADLINE_PARAMETERS = "deadline parameters is incomplete. " +
            "Proper syntax: deadline [instruction] /by [deadline]";
    public final static String INSUFFICIENT_EVENT_PARAMETERS = "event parameters is incomplete. " +
            "Proper syntax: event [eventName] /from [startDate] /to [endDate]";
    public final static String INSUFFICIENT_MARK_PARAMETERS = "mark/unmark parameters is incomplete. " +
            "Proper syntax: mark/unmark [task number]";
    public final static String TASK_NOT_FOUND = "task not found. " + "Please check the task number again.";

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
}
