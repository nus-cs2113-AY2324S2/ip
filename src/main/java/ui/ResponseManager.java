package ui;

import command.CommandType;

/**
 * The ResponseManager class handles the response to the user.
 */
public class ResponseManager {
    private static final String LOGO =
            "███████╗██╗   ██╗██╗  ██╗███████╗\n" +
            "╚══███╔╝██║   ██║██║ ██╔╝██╔════╝\n" +
            "  ███╔╝ ██║   ██║█████╔╝ █████╗  \n" +
            " ███╔╝  ██║   ██║██╔═██╗ ██╔══╝  \n" +
            "███████╗╚██████╔╝██║  ██╗███████╗\n" +
            "╚══════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝\n";
    public static final String TODO = "todo\n";
    public static final String DEADLINE = "deadline\n";
    public static final String EVENT = "event\n";
    private static final String INDENTATION_LINE =
            "____________________________________________________________";
    private static final String BYE_MESSAGE =
            "Bye. Hope to see you again soon!\n"
            + "Don't miss me~\n";
    private static final String TASK_ADDED_MESSAGE =
            "Great! I've added this task:\n";
    public static final String INDEX_ERROR_MESSAGE =
            "please enter a valid index!\n";
    public static final String COMMAND_ERROR =
            "please enter a valid command\n" +
            "type help for the manual if needed :)\n";
    public static final String FORMAT_ERROR_MESSAGE =
            "please follow the valid format for ";
    public static final String BLANK_MSG_ERROR =
            "make sure the required field is not empty.\n type HELP for help\n";
    private static final String LIST_TASK_MESSAGE =
            "Here are the tasks in your list:\n";
    private static final String UNMARKED_MESSAGE =
            "Haiz, I've marked this task as not done yet:\n";
    private static final String MARKED_MESSAGE =
            "Good job! I've marked this task as done for you:\n";
    private static final String END_LINE = System.lineSeparator();
    private static final String DELETE_MESSAGE =
            "Alright. I've removed the task:\n";
    public static final String SAVE_FILE_ERROR =
            "OOPs, something wrong with file saving :(\n";
    public static final String LOAD_FILE_ERROR =
            "OOPs, something wrong with file loading :(\n";
    public static final String NO_FILE_ERROR =
            "Looks like the file does not exist TAT\n";
    public static final String CREATE_FILE_MESSAGE =
            "You will start a fresh new journey!\n";
    public static final String RETURN_MESSAGE =
            "It's always nice to see you again!\n";
    public static final String FIND_MESSAGE =
            "Here are the matching tasks in your list:\n";
    public static final String DATE_FORMAT_ERROR =
            "Please follow the format for date & time:\n" +
            "For date: dd/MM/yyyy\n" +
            "For time: HHmm\n";
    public static final String HELP_MESSAGE =
            "\n===========================ZUKE   MANUAL=========================\n" +
            "ADD TODO TASK:                                   todo <task name>\n" +
            "ADD DEADLINE TASK:                deadline <task name> /by <date>\n" +
            "ADD EVENT TASK:         event <task name> /from <date> /to <time>\n" +
            "LIST ALL TASKS:                                              list\n" +
            "MARK/UNMARK A TASKS:                    mark/unmark <task number>\n" +
            "DELETE A TASK:                               delete <task number>\n" +
            "FIND WITH WORD:                                 find /w <keyword>\n" +
            "FIND WITH TIME:                                    find /t <date>\n" +
            "EXIT:                                                         bye\n" +
            "=================================================================\n";
    public static final String EMPTY_LIST_MSG =
            "OOPS your task list is still empty, pls add some tasks\n";
    public static final String DELETE_EMPTY_LIST_MSG =
            "OOPS seems like there's nothing to delete\n";
    public static final String MARKED_TASK_MSG = "This task is already marked\n";
    public static final String UNMARKED_TASK_MSG = "This task is already unmarked\n";
    public static final String TIME_PARADOX_MSG =
            "U HAVE REACHED A TIME PARADOX!!!\n" +
            "The start time cannot be after the end time\n";
    public static final String GREET_MSG = "Hello! I'm Zuke\n" +
            "What can I do for you?\n" +
            "\nNeed help? Command help for the manual :>\n";
    public static final String NO_TASKS_FOUND_MSG =
            "Sorry, I can't find any tasks in the task list contains the\n" +
            " input information :(\n";

    /**
     * Prints the response to the user with indentation line.
     * 
     * @param response the response to be printed.
     */
    public static void indentPrint(String response) {
        System.out.println(INDENTATION_LINE);
        System.out.println(response + INDENTATION_LINE);
    }
    
    /**
     * Prints the greeting message to the user.
     */
    public static void greet() {
        System.out.println("Hello from\n" + LOGO);
        indentPrint(GREET_MSG);
    }

    /**
     * Prints the goodbye message to the user.
     */
    public static void sayGoodbye() {
        indentPrint(BYE_MESSAGE);
    }

    /**
     * Prints the list of tasks to the user.
     * 
     * @param taskList the list of tasks created by user to be printed.
     */
    public static void listTaskToUser(String taskList) {
        indentPrint(LIST_TASK_MESSAGE + taskList);
    }

    public static void sendTaskAddedToUser(String messageToPrint) {
        indentPrint(TASK_ADDED_MESSAGE + " " +
                messageToPrint + END_LINE);
    }

    /**
     * Prints the help message containing the chatbot's manual.
     */
    public static void sendHelpMessage() {
        indentPrint(HELP_MESSAGE);
    }

    public static void sendEmptyListMsg() {
        indentPrint(EMPTY_LIST_MSG);
    }

    /**
     * Prints the action on done by the Zuke chatbot to the user bases on the action.
     * 
     * @param action the action that has performed on the tasks.
     * @param msgTobePrinted the message to be printed.
     */
    public static void printActionOnTasks(CommandType action, String msgTobePrinted) {
        switch(action) {
        case MARK:
            indentPrint(MARKED_MESSAGE + " " + msgTobePrinted + END_LINE);
            break;

        case UNMARK:
            indentPrint(UNMARKED_MESSAGE + " " + msgTobePrinted + END_LINE);
            break;

        case DELETE:
            indentPrint(DELETE_MESSAGE + " " + msgTobePrinted + END_LINE);
            break;

        case FIND:
            indentPrint(FIND_MESSAGE + msgTobePrinted + END_LINE);
            break;

        default:
            break;
        }
    }
}
