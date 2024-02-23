public class Guide{
    public static String MARK_AND_UNMARK_REQUEST_FORMAT = "Please input request in the following format:\n"
            + "mark [task number]\n"
            + "unmark [task number]";

    public static String OUT_OF_BOUND = "The number you've inputted is larger than the number of tasks present\n";

    public static String DELETE_REQUEST_FORMAT= "Please input request in the following format:\n"
            + "delete [task number]";
    public static String TODO_REQUEST_FORMAT = "Please input request in the following format:\n"
            + "todo [task description]";

    public static String DEADLINE_REQUEST_FORMAT = "Please input request in the following format:\n"
            + "deadline [task description] /by [due date]";

    public static String EVENT_REQUEST_FORMAT = "Please input request in the following format:\n"
            + "event [task description] /from [start of event] /to [end of event]";

    public static String REQUESTS_FORMAT = "Please input request in the following format:\n"
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
}