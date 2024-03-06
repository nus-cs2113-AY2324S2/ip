package Common;

public class Messages {

    public static final String MESSAGE_GREETING = "Hello! I'm Chelle.\nI like to talkity talkity talk!";
    public static final String MESSAGE_SYSTEM_PREFIX = "Chelle: ";
    public static final String MESSAGE_INPUT_PREFIX = "You: ";
    public static final String MESSAGE_INVALID_COMMAND = "Chelle: Invalid command. Please start your command with one of the following commands: \n" +
            "       'list', 'mark', 'unmark', 'todo', 'deadline', 'event', 'delete', or 'find'.";
    public static final String MESSAGE_BYE = "Chelle: Bye! Hope to see you again soon!";
    public static final String MESSAGE_NO_TASKS = "No tasks to display.";
    public static final String MESSAGE_COMMAND_ERROR = "Chelle: Error reading command";
    public static final String MESSAGE_INVALID_MARK_FORMAT = "Chelle: Invalid format. Use 'mark ___'.";
    public static final String MESSAGE_INVALID_UNMARK_FORMAT = "Chelle: Invalid format. Use 'unmark ___'.";
    public static final String MESSAGE_INVALID_TODO_FORMAT = "Chelle: Invalid format. Use 'todo ___'.";
    public static final String MESSAGE_INVALID_DEADLINE_FORMAT = "Chelle: Invalid format. Use 'deadline ___ /by ___' or" +
            " 'deadline ___ /by DD/MM/YYYY hhmm'.";
    public static final String MESSAGE_INVALID_EVENT_FORMAT = "Chelle: Invalid format. Use 'event ___ /from ___ /to ___'.";
    public static final String MESSAGE_INVALID_DELETE_FORMAT = "Chelle: Invalid format. Use 'delete ___'.";
    public static final String MESSAGE_INVALID_FIND_FORMAT = "Chelle: Invalid format. Use 'find ___'.";
    public static final String MESSAGE_MARKED = "Chelle: Nice! I've marked this task as done:\n        ";
    public static final String MESSAGE_UNMARKED = "Chelle: OK, I've marked this task as not done yet:\n        ";
    public static final String MESSAGE_INVALID_INDEX = "Chelle: Invalid task index.";
    public static final String MESSAGE_NO_MATCHING_TASKS = "Chelle: Sorry, no tasks match your search.";
    public static final String MESSAGE_MATCHING_TASKS = "Chelle: Here are the matching tasks in your list:";
}
