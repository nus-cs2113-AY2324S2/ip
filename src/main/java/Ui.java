import java.util.ArrayList;
import java.util.Scanner;
/**
 * Provides a user interface for interacting with the program.
 */
public class Ui {
    /**
     * Scanner object for user input.
     */
    private Scanner in;

    /**
     * Constructs a new Ui object with a Scanner for input.
     */
    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Retrieves user input from the console.
     *
     * @return The user input as a String.
     */
    public String getUserInput() {
        return in.nextLine();
    }

    /**
     * Prints a message to the console.
     *
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints an error message indicating failure to load tasks from a file.
     */
    public void showLoadingError() {
        System.err.println("Error loading tasks from file.");
    }

    /**
     * Prints a welcome message to the console.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Brennan!");
        System.out.println("What can I do for you?\n");
    }

    /**
     * Displays a line separator.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message confirming the deletion of a task.
     *
     * @param indexTask   The index of the deleted task.
     * @param removedTask The removed task.
     */
    public static void deleteTaskDisplay(int indexTask, Task removedTask) {
        System.out.println("Command received. I've removed this task:");
        System.out.println(indexTask + ". " + "[" + removedTask.getStatusIcon() + "]" + removedTask.description);
        System.out.println("Currently you have " + TaskList.tasks.size() + " tasks in the list below.");
    }

    /**
     * Displays a message when no tasks are found.
     */
    public static void noTasksFoundDisplay() {
        System.out.println("____________________________________________________________");
        System.out.println("No tasks found.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays matching tasks found based on search criteria.
     *
     * @param matchingTasks The list of matching tasks.
     */
    public static void showTasksFoundDisplay(ArrayList<Task> matchingTasks) {
        System.out.println("Here are all the tasks that match your search keyword: ");
        System.out.println("____________________________________________________________");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println((i + 1) + ". " + " " + "[" + matchingTasks.get(i).typeOfTask + "]" + "[" + matchingTasks.get(i).getStatusIcon() + "]" + matchingTasks.get(i).description);
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message confirming the marking of a task as completed.
     *
     * @param indexTask The index of the completed task.
     */
    public static void markedTasksAsCompletedDisplay(int indexTask) {
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(indexTask + ". " + "[" + TaskList.tasks.get(indexTask - 1).getStatusIcon() + "]" + TaskList.tasks.get(indexTask - 1).description);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the list of tasks.
     */
    public static void listTasksDisplay() {
        System.out.println("Here are the tasks in your list: ");
        System.out.println("____________________________________________________________");
        for (int i = 0; i < TaskList.tasks.size(); i++) {
            System.out.println((i + 1) + ". " + " " + "[" + TaskList.tasks.get(i).typeOfTask + "]" + "[" + TaskList.tasks.get(i).getStatusIcon() + "]" + TaskList.tasks.get(i).description);
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message confirming the marking of a task as not completed.
     *
     * @param indexTask The index of the task.
     */
    public static void markedTasksAsNotCompletedDisplay(int indexTask) {
        System.out.println("____________________________________________________________");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println((indexTask) + ". " + "[" + TaskList.tasks.get(indexTask - 1).getStatusIcon() + "]" + TaskList.tasks.get(indexTask - 1).description);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message confirming the addition of a new task.
     *
     * @param newTask               The newly added task.
     * @param currentNumberOfTasks The current number of tasks.
     */
    public static void indicateNewTaskDisplay(Task newTask, int currentNumberOfTasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Well done, you've added a new task: ");
        System.out.println("[" + newTask.typeOfTask + "]" + "[" + newTask.getStatusIcon() + "]" + newTask.description);
        System.out.println("Currently you have " + currentNumberOfTasks + " task(s) in your list!");
        System.out.println("____________________________________________________________");
    }
}