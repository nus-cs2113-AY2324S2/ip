package jake.common;

public class Messages {
    public static final String MESSAGE_LINE_STRING = "____________________________________________________________\n";
    public static final String MESSAGE_GREETING = "Hello! I'm Jake\n" + "What can I do for you? \n" + MESSAGE_LINE_STRING;
    public static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_LISTED_TASKS = "Current commands being executed: ";
    public static final String MESSAGE_INVALID_COMMAND = "Command not recognised! Please try again!";
    public static final String MESSAGE_INVALID_FILEPATH = "File cannot be found!";
    public static final String MESSAGE_TASK_DOES_NOT_EXIST = "Task does not exist!";
    public static final String MESSAGE_TASK_MARKED = "Successfully marked";
    public static final String MESSAGE_TASK_UNMARKED = "Successfully unmarked";
    public static final String MESSAGE_TASK_ADDED = MESSAGE_LINE_STRING + "Got it! I have successfully added: \n" + 
            "    " + "%1$s \n" + "You have a total of %2$d tasks in the list \n" + MESSAGE_LINE_STRING;
    public static final String MESSAGE_TASK_DELETED = MESSAGE_LINE_STRING + "Got it! I have successfully removed: \n" + 
            "    " + "%1$s \n" + "You have a total of %2$d tasks in the list \n" + MESSAGE_LINE_STRING;
    public static final String MESSAGE_TASK_ERROR_ENCOUNTERED = "Something went wrong...: %1$s";
}
