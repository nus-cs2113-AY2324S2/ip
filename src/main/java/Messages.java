public class Messages{
    public static final String GREET_USER_MESSAGE = "Hello! I'm Ms Chatty :)";
    public static final String PROMPTS_USER_INPUT = "What can I do for you?";
    public static final String MARK_AND_UNMARK_REQUEST_FORMAT = "Please input request in the following format:\n"
            + "mark [task number]\n"
            + "unmark [task number]";
    public static final String OUT_OF_BOUND = "The number you've inputted is larger than the number of tasks present\n";
    public static final String DELETE_REQUEST_FORMAT= "Please input request in the following format:\n"
            + "delete [task number]";
    public static final String TODO_REQUEST_FORMAT = "Please input request in the following format:\n"
            + "todo [task description]";
    public static final String DEADLINE_REQUEST_FORMAT = "Please input request in the following format:\n"
            + "deadline [task description] /by [due date]";
    public static final String EVENT_REQUEST_FORMAT = "Please input request in the following format:\n"
            + "event [task description] /from [start of event] /to [end of event]";
    public static final String REQUESTS_FORMAT = "Please input request in the following format:\n"
            + "To mark task as done:\n"
            + "mark [task number]\n\n"
            + "To mark task as undone:\n"
            + "unmark [task number]\n\n"
            + "To add task without date or time\n"
            + "todo [task description]\n\n"
            + "To add task that needs to be done before a specific date and/or time\n"
            + "deadline [task description] /by [due date]\n\n"
            + "To add tasks that start at a specific date and/or time and ends at a specific date and/or time\n"
            + "event [task description] /from [start of event] /to [end of event]\n\n"
            + "To delete task\n"
            + "delete [task number]\n\n";
    public static final String PRINT_TASK_LIST = "Here are the tasks in your list:";
    public static final String MARK_DONE_MESSAGE = "Nice! I've marked this task as done:";
    public static final String UNMARK_DONE_MESSAGE = "OK, I've marked this task as not done yet:";
    public static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";
    public static final String FILE_CORRUPTED_MESSAGE = "Data file is corrupted: ";
    public static final String ERROR_SAVING_DATA_MESSAGE = "Error saving tasks to file: ";
    public static final String ERROR_READING_FILE_MESSAGE = "Error reading tasks from file: ";
    public static final String TASKS_SAVED_MESSAGE = "Tasks saved successfully.";
    public static final String FAREWELL_MESSAGE = "Bye! Hope to see you again :)";

}