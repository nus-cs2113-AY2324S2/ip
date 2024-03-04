package oley.commands;

import oley.tasks.Task;
import oley.tasks.TaskList;

import java.util.Objects;
import java.util.Scanner;

/**
 * Represents methods that are used to communicate with users.
 * It deals with interactions with users.
 */
public class Ui {
    /**
     * Represents a line of divider on the screen.
     * It is used to divide between chatbot and user.
     */
    public static void lineBreaker() {
        System.out.println(" ");
        System.out.println("    *~~~~~*~~~~~*~~~~~*~~~~~*~~~~~*~~~~~*~~~~~*");
    }

    /**
     * Represents greeting content of the chatbot on the screen.
     * It is used to greet the user at initialisation stage.
     */
    public static void printGreeting() {
        String logo = "  _____  __       \n"
                + " /  _  \\|  | ____ ___  ___ \n"
                + "|  | |  |  |/ ___ \\  \\/  /\n"
                + "|  |_|  |  |  ____/\\    /\n"
                + " \\_____/|__|\\_____|/   /\n"
                + "                  /___/";
        System.out.println("Greetings from\n" + logo);
        lineBreaker();
        System.out.println("    Hello, I'm your cute and lovely friend Oley.");
        System.out.println("    What can I do for you?");
        lineBreaker();
    }

    /**
     * Represents an error message on the screen.
     * It is used to show that target file does not exist.
     */
    public static void printFileNotFound() {
        System.out.println("    OOPS, the file does not exist! I will now create one for you~ (*o*)");
    }

    /**
     * Represents an error message on the screen.
     * It is used to show that data file is not successfully created.
     */
    public static void printFileNotCreated() {
        System.out.println("    Sorry! I am unable to create file for you. (>.<)");
    }

    /**
     * Represents an error message on the screen.
     * It is used to show that there are some errors in target file.
     */
    public static void printFileCorrupted() {
        System.out.println("    OOPS, the file seems to be corrupted!");
    }

    /**
     * Represents an error message on the screen.
     */
    public static void printError() {
        System.out.println("    OOPS, we have encountered an error!");
    }

    /**
     * Represents a warning message on the screen.
     * It is used to ask the user to re-enter the time in acceptable format.
     *
     * @param string Type of time that needs to be re-entered.
     */
    public static void printCorrectFormat(String string) {
        if (Objects.equals(string, "from")) {
            System.out.println("    Please re-enter the starting time of the event!");
        } else if (Objects.equals(string, "to")) {
            System.out.println("    Please re-enter the ending time of the event!");
        } else if (Objects.equals(string, "by")) {
            System.out.println("    Please re-enter the due time of the task!");
        }
        System.out.println("    The accepted format of timing should be yyyy-MM-dd-HHmm.");
        System.out.println("    For example, 2024-06-07-2359.");
    }

    /**
     * Prints event and deadline that end before the required time.
     *
     * @param tasks Task list that contains tasks within the specified time.
     */
    public static void printTasksWithinTime(TaskList tasks) {
        System.out.println("    Here are the tasks before the timing:");
        for (Task task : tasks) {
            System.out.println("    " + task.toString());
        }
    }

    /**
     * Prints tasks that contain the keyword.
     *
     * @param tasks Task list that contains tasks with the keyword.
     */
    public static void printTasksWithKeyword(TaskList tasks) {
        System.out.println("    Here are the tasks containing the keyword:");
        for (Task task : tasks) {
            System.out.println("    " + task.toString());
        }
    }

    /**
     * Represents an error message on the screen.
     * It is used to show that due time is not recognised.
     */
    public static void printDeadlineNotSpecified() {
        System.out.println("    A specific deadline would be better for you to complete your task on time! (* ^ *)");
        System.out.println("    You may use /by to indicate the time.");
    }

    /**
     * Represents an error message on the screen.
     * It is used to show that start or end time is not recognised.
     */
    public static void printEventNotSpecified() {
        System.out.println("    A specific timing of the event would be clearer! (* ^ *)");
        System.out.println("    You may use /from and /to to indicate the starting and ending time.");
    }

    /**
     * Represents an error message on the screen.
     * It is used to show unsuccessful writing to file.
     */
    public static void printFailToWrite() {
        System.out.println("    Write to file failed.");
    }

    /**
     * Prints the message of task being successfully added to the task list.
     *
     * @param taskName Name of task added.
     */
    public static void printTaskAdded(Task taskName) {
        System.out.println("    " + "added: " + taskName);
    }

    /**
     * Prints the size of task list after adding or deleting.
     *
     * @param size Number of tasks in the task list.
     */
    public static void printTaskNumber(int size) {
        if (size <= 1) {
            System.out.println("    Now you have " + size + " task in the list.");
        } else {
            System.out.println("    Now you have " + size + " tasks in the list.");
        }
    }

    /**
     * Prints the message of task being successfully deleted from the task list.
     *
     * @param taskToBeDeleted Task to be deleted.
     */
    public static void printTaskDeleted(String taskToBeDeleted) {
        System.out.println("    Sure! (0 u 0) I have removed this task:");
        System.out.println("    " + taskToBeDeleted);
    }

    /**
     * Represents an error message on the screen.
     * It is used to show clear file unsuccessfully.
     */
    public static void printFailToClear() {
        System.out.println("    Clear file failed.");
    }

    /**
     * Represents exiting content of the chatbot on the screen.
     * It is used to say goodbye to the user when user typed "bye" command.
     */
    public static void exit() {
        System.out.println("    Bye~ Feel free to talk to me anytime. I will always be here waiting for you. (0~0)");
    }

    public static void printTasks() {
        System.out.println("    Here are the tasks in your list:");
    }

    /**
     * Prints the task on the screen.
     *
     * @param taskNumber Index of the task in the task list
     * @param taskName Task to be printed in its toString() format.
     */
    public static void printTask(int taskNumber, String taskName) {
        System.out.println("    " + taskNumber + "." + taskName);
    }

    /**
     * Represents an error message on the screen.
     * It is used to show that the number entered is greater than the size of the task list.
     *
     * @param taskNumber Index of the task.
     */
    public static void printMarkExceedRange(int taskNumber) {
        System.out.println("    Task " + taskNumber + " does not exist!");
    }

    /**
     * Represents a warning message on the screen.
     * It is used to show that the task user want to mark is already marked.
     */
    public static void printMarkedAlready() {
        System.out.println("    This task has been marked as done already!");
    }

    /**
     * Prints the message of task being successfully marked in the task list.
     *
     * @param task Name of task marked.
     */
    public static void printMarked(Task task) {
        System.out.println("    Good job! I've marked this task as done:");
        System.out.println("    " + task);
    }

    /**
     * Represents a warning message on the screen.
     * It is used to show that the task user want to unmark is not yet marked.
     */
    public static void printUnmarkFailed() {
        System.out.println("    This task hasn't been done yet!");
    }

    /**
     * Prints the message of task being successfully unmarked in the task list.
     *
     * @param task Name of task unmarked.
     */
    public static void printUnmarked(Task task) {
        System.out.println("    Sure~ I've marked this task as not done yet:");
        System.out.println("    " + task);
    }

    /**
     * Represents an error message on the screen.
     * It is used to show delete task unsuccessfully.
     */
    public static void printFailToDelete() {
        System.out.println("    The task you are trying to delete does not exist! (>.<)");
    }

    /**
     * Represents an error message on the screen.
     * It is used to show that a specific time is not provided after "time" command.
     */
    public static void printFailToFindTiming() {
        System.out.println("    Please provide a specific timing! (>.<)");
    }

    /**
     * Represents an error message on the screen.
     * It is used to show that a keyword is not provided after "find" command.
     */
    public static void printFailToFindTasks() {
        System.out.println("    Please provide one keyword to search! (>.<)");
    }

    /**
     * Represents an error message on the screen.
     * It is used to show that the description of task is missing.
     */
    public static void printMissingDescription() {
        System.out.println("    The description of a task cannot be empty! (>.<)");
    }

    /**
     * Represents an error message on the screen.
     * It is used to show that the command entered by user is invalid, cannot be recognized.
     */
    public static void printInstructionNotUnderstood() {
        System.out.println("    So sorry, I do not understand the commands. I will try to improve!! (o ^ o)");
        System.out.println("    Meanwhile, you can use todo, deadline or event to indicate the type of tasks.");
    }

    /**
     * Reads commands from user input.
     *
     * @return New instructions entered by the user.
     */
    public static String readCommand() {
        Scanner in = new Scanner(System.in);
        String message;
        message = in.nextLine();
        Ui.lineBreaker();
        return message;
    }

    /**
     * Represents a warning message on the screen.
     * It is used to show that the due time being set for the deadline is before the current time.
     */
    public static void printDeadlinePassed() {
        System.out.println("    OOPS! The deadline is passed already. Please change it to sometime after now.");
    }

    /**
     * Represents a warning message on the screen.
     * It is used to show that the end time of the event is before the start time.
     */
    public static void printToBeforeFrom() {
        System.out.println("    OOPS! The end time is before the starting time. Please change it to sometime after the starting time.");
    }

    /**
     * Represents an error message on the screen.
     * It is used to show that the index of task is missing.
     */
    public static void printMissingNumber() {
        System.out.println("    The index of task should be provided! (>.<)");
    }
}
