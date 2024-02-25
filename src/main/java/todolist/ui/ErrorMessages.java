package todolist.ui;

public class ErrorMessages {
    public static final String ERROR_MESSAGE_TASK_DOES_NOT_EXIST = "Task does not exist";
    public static final String ERROR_MESSAGE_INVALID_COMMAND = "Invalid command, use [help] to show all the commands";
    public static final String MESSAGE_DIVIDER = "------------------------------------------";

    public static final String ERROR_MESSAGE_TASK_FORMAT_INCORRECT = "Incorrect task format, use [help] to show correct format";

    public static final String ERROR_MESSAGE_TODOTASK_FORMAT_INCORRECT = "TodoTask cannot be empty";

    public static void taskDoesNotExistErrorMessage() {
        System.out.println(ERROR_MESSAGE_TASK_DOES_NOT_EXIST);
    }

    public static void invalidCommandErrorMessage() {
        System.out.println(ERROR_MESSAGE_INVALID_COMMAND);
    }

    public static void taskFormatIncorrectErrorMessage() {
        System.out.println(ERROR_MESSAGE_TASK_FORMAT_INCORRECT);
    }

    public static void todoTaskFormatIncorrectErrorMessage() {
        System.out.println(ERROR_MESSAGE_TODOTASK_FORMAT_INCORRECT);
    }




}
