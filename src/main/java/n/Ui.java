/**
 * The Ui class handles user interactions and displays
 * messages to the console.
 *
 * @author  anneleong
 * @version 1.0
 * @since   2024-03-07
 */
package n;

import n.task.Task;
import n.task.Type;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import static n.TaskList.taskList;

public class Ui {
    // Constants for messages and formatting
    public static final String BYE = "Bye. Hope to see you again soon!";
    public static final String EMPTY_TASKLIST_MESSAGE = "no tasks added, wake up pleasee!";
    public static final String NO_SEARCH_RESULT_MESSAGE = "no matching tasks";
    public static final String NO_TASK_TYPE_ERROR = "oHno, you did not specify your task type :O";
    public static final String NO_TASK_INDEX_ERROR = "Task number not provided, please try again ...";
    public static final String INVALID_TASK_INDEX_ERROR = "invalid task!";
    public static final String INPUT_INSTRUCTION = "    Please input in this format:\n";
    public static final String TODO_INPUT_FORMAT = "    todo [task]";
    public static final String EVENT_INPUT_FORMAT = "    event [task] /from [start time] /to [end time]";
    public static final String DEADLINE_INPUT_FORMAT = "    deadline [task] /by [deadline]";
    public static final String TASK_INDEX_OUT_OF_BOUNDS_ERROR = "Task not found :p";
    public static final String SAVE_AS_FILE_ERROR = "Ohno, error occurred when saving file :/";
    public static final String LOGO = " ____   ___\n"
            + "|    \\ |   |\n"
            + "|     \\|   |\n"
            + "|          |\n"
            + "|   |\\     |\n"
            + "|___| \\____| .chatbot :)";
    public static final String LINE = "    ____________________________________________________________\n";
    public static final String INDENT = "    ";
    private final Scanner input;
    /**
     * Constructs a Ui object with a Scanner for user input.
     */
    public Ui() {
        input = new Scanner(System.in);
    }
    /**
     * Prints a line of underscores for formatting.
     */
    public static void printLine() {
        System.out.println(LINE);
    }
    /**
     * Prints a formatted message surrounded by lines.
     *
     * @param message The message to be printed.
     */
    public static void printMessage(String message) {
        printLine();
        System.out.println(INDENT+message);
        printLine();
    }
    /**
     * Prints a welcome message.
     */
    public void printWelcome(){
        System.out.println("Hello from\n" + LOGO);
        printMessage("Hello! I'm N :) \n" +INDENT+ "What can I do for you?");
    }
    /**
     * Prints the task list or an empty message if the list is empty.
     */
    public void printTaskList() {
        if (taskList.isEmpty()) {
            printMessage(EMPTY_TASKLIST_MESSAGE);
        } else {
            printLine();
            System.out.println(INDENT+"Here are the tasks in your list:");
            for (Task task : taskList) {
                System.out.println(INDENT+ task);
            }
            printLine();
        }
    }
    /**
     * Prints a message to indicate that a task has been added.
     */
    public static void printTaskAddedMessage() {
        if (taskList.size() <= 1) {
            printMessage("Got it, " +taskList.get(taskList.size() - 1).getTaskType()+ " task has been added:\n" + "    "
                    +taskList.get(taskList.size() - 1).toString()+
                    "\n    Now you have 1 task in the list");
        } else {
            printMessage("Got it, " +taskList.get(taskList.size() - 1).getTaskType()+ " task has been added:\n" + "    "
                    +taskList.get(taskList.size() - 1).toString()+
                    "\n    Now you have "+taskList.size()+" tasks in the list");
        }
    }
    /**
     * Prints a message to indicate that a task has been deleted.
     *
     * @param indexToDelete The index of the deleted task.
     */
    public static void printTaskDeletedMessage(int indexToDelete) {
        printMessage("Noted, I have removed the following task:\n" +
                "    " +taskList.get(indexToDelete).toString()+ "\n" +
                "    Number of Tasks Remaining: " +(taskList.size() - 1));
    }
    /**
     * Prints a message indicating that the task list has been saved.
     *
     * @param filePath The path where the task list is saved.
     */
    public static void printTaskListSavedMessage(Path filePath) {
        printMessage("Your Task List has been saved, find it at "+filePath);
    }
    /**
     * Prints a message indicating that changing the task status is not needed.
     *
     * @param taskIndex The index of the task.
     * @param taskStatus The status of the task.
     */
    public static void printChangeTaskStatusNotNeededMessage(int taskIndex, boolean taskStatus) {
        String outputMessage = (taskStatus) ?
                //output message if task has already been marked done
                "Task " +taskList.get(taskIndex).getIndex()+ " is already completed!" :
                //output message if task has not been marked done
                "Task " +taskList.get(taskIndex).getIndex()+ " is already unmarked, complete other tasks!";
        printMessage(outputMessage);
    }
    /**
     * Prints a message indicating that the task status has changed.
     *
     * @param taskIndex The index of the task.
     * @param newStatus The new status of the task.
     */
    public static void printTaskStatusChangedMessage(int taskIndex, boolean newStatus) {
        String outputMessage = (newStatus) ?
                //output message when task is marked done
                "Task " +(taskIndex + 1)+ " marked done, yay! :)" :
                //output message when task is unmarked
                "Okay, task " +(taskIndex + 1)+ " unmarked, let's complete it soon ~.o.~";
        printMessage(outputMessage);
    }
    /**
     * Prints an error message for an incorrect task format.
     *
     * @param taskType The type of the task (Event, Deadline, ToDo).
     */
    public static void printFormatErrorMessage(Type taskType) {
        String type;
        String format;
        switch(taskType) {
            case Event:
                type = "event";
                format = EVENT_INPUT_FORMAT;
                break;
            case Deadline:
                type = "deadline";
                format = DEADLINE_INPUT_FORMAT;
                break;
            default:
                type = "todo";
                format = TODO_INPUT_FORMAT;
                break;
        }
        printMessage("Wrong format for " +type+ " task! \n" +
                INPUT_INSTRUCTION+
                format);
    }
    /**
     * Prints the search results to the console.
     *
     * @param searchResult An ArrayList of String representations of tasks to be printed.
     */
    public void printSearchResult(ArrayList<String> searchResult) {
        if (searchResult.isEmpty()) {
            printMessage(NO_SEARCH_RESULT_MESSAGE);
        } else {
            printLine();
            System.out.println(INDENT+"Here are the tasks I found in your list:");
            for (String task : searchResult) {
                System.out.println(INDENT+ task);
            }
            printLine();
        }
    }
    /**
     * Handles user input messages and performs corresponding actions.
     */
    public void handleMessages() {
        String message = input.nextLine();
        if (message.startsWith("bye")) {
            printMessage(BYE);
            input.close();
        } else if (message.equalsIgnoreCase("list")) {
            printTaskList();
            handleMessages();
        } else if (message.trim().startsWith("unmark")) {
            TaskList.unmarkTask(message);
            handleMessages();
        } else if (message.trim().startsWith("mark")) {
            TaskList.markTask(message);
            handleMessages();
        } else if (message.trim().startsWith("delete")) {
            TaskList.deleteTask(message);
            handleMessages();
        } else if (message.trim().equalsIgnoreCase("save")) {
            N.storage.saveTaskList();
            handleMessages();
        } else if (message.trim().startsWith("find")) {
            ArrayList<String> searchResult = TaskList.findTask(message);
            printSearchResult(searchResult);
            handleMessages();
        } else {
            TaskList.addTask(message);
            handleMessages();
        }
    }
}
