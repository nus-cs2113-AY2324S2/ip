public class Message {
    public static final String GREET = "Hello! I am Jane.\nWhat can I do for you?\n";
    public static final String EXIT = "Bye. Hope to see you again soon!\n";
    public static final String TASK_ADDED = "Got it. I've added this task:\n";
    public static final String TASK_REMOVED = "Noted. I've removed this task:\n";
    public static final String MARK_AS_DONE = "Nice! I've marked this task as done:\n";
    public static final String MARK_AS_UNDONE = "OK, I've marked this task as not done yet:\n";
    public static final String LIST_HEADER = "Here are the tasks in your list:";
    public static final String MATCHING_LIST_HEADER = "Here are the matching tasks in your list:";
    public static final String INPUT_NOT_UNDERSTOOD_ERROR = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String TODO_EMPTY_DESCRIPTION_ERROR = "OOPS!!! The description of a todo cannot be empty.";
    public static final String DEADLINE_EMPTY_DESCRIPTION_ERROR = "OOPS!!! The description of a deadline cannot be empty.";
    public static final String EVENT_EMPTY_DESCRIPTION_ERROR = "OOPS!!! The description of a event cannot be empty.";
    public static final String INEXISTENT_FILE = "File does not exist.";
    public static final String CORRUPTED_FILE = "File data is corrupted.";

    /**
     * Generates a message indicating the current number of tasks in the list.
     *
     * @param count The number of tasks in the list.
     * @return A message indicating the number of tasks.
     */
    public static String numberOfTasks(int count) {
        return "Now you have " + count + " tasks in the list.");
    }

    /**
     * Generates an error message for an empty description in a task.
     *
     * @param taskType The type of task (e.g., todo, deadline, event).
     * @return An error message for an empty description in the specified task type.
     */
    public static String emptyDescriptionError(String taskType) {
        return "OOPS!!! The description of a " + taskType + " cannot be empty.";
    }
}
