package Ui;

import TaskList.TaskList;
import tasks.Task;


/**
 * Represents all interactions between PeeKay and the user.
 */
public class Ui {

    public Ui() {

    }

    /**
     * Prints a dotted line out between commands.
     */
    public static void showLine(){
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Prints a welcome message when PeeKay is first activated.
     */
    public static void sayHi(){
        System.out.println("\t Hello! I'm PeeKay");
        System.out.println("\t What can I do for you?");
    }

    /**
     * Prints a goodbye message when PeeKay is closed.
     */
    public static void closeApp(){
        System.out.println("\t Bye. Hope to see you again soon!");
    }

    /**
     * Prints confirmation of deleting a task.
     * @param description Name of the task that was deleted
     */
    public static void printDeleteMessage(String description){
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t   " + description);
    }

    /**
     * Prints number of tasks remaining. This function is called after the user adds or deletes a task.
     * @param taskNumber Number of tasks in the list of tasks
     */
    public static void printRemainingTaskNumber(int taskNumber){
        String task = (taskNumber == 1) ? "task " : "tasks ";
        System.out.println("\t Now you have " + taskNumber + " " + task + "in the list.");
    }

    /**
     * Prints confirmation of the task that was added.
     * @param task Task that was added
     */
    public static void printTaskAddedMessage(Task task) {
        System.out.println("\t Got it. I've added this task: ");
        printSingleTask(task);
    }

    /**
     * Prints <code>task</code> in a string format
     * @param task Task object
     */
    public static void printSingleTask(Task task) {
        System.out.println(("\t   " + task.toString()));
    }

    /**
     * Prints error message to the terminal when an error is caught.
     * @param errorMessage Error message that is thrown
     */
    public static void printError(String errorMessage){
        System.out.println("\t Error: " + errorMessage);
    }

    /**
     * Prints a message indicating unknown input from the user.
     */
    public static void printUnknownInputMessage(){
        System.out.println("\t I've not seen this input before. Please tell me something else I can help you with.");
    }

    /**
     * Prints the list of tasks.
     * @param tasks The list of tasks
     */
    public static void printTaskList(TaskList tasks){
        System.out.println("\t Here are the tasks in your list:");
        for (int x = 0; x < tasks.size(); x++) {
            System.out.println("\t  " + (x + 1) + "." + tasks.get(x).toString());
        }
    }

    /**
     * Prints a message indicating that a task has been marked as done.
     * @param task The task marked as done
     */
    public static void printMarkedAsDoneMessage(Task task){
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t " + task.toString());
    }

    /**
     * Prints a message indicating that a task has been marked as not done yet.
     * @param task The task marked as not done yet
     */
    public static void printMarkedAsUndoneMessage(Task task){
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.println("\t " + task.toString());
    }

    /**
     * Prints an error message indicating that the saved file was not found.
     */
    public static void printLoadingErrorMessage(){
        System.out.println("\t Saved file not found.");
    }

    /**
     * Prints an error message indicating that changes could not be saved to the file.
     */
    public static void printChangesNotSavedMessage(){
        System.out.println("\t Could not save changes to file.");
    }

    /**
     * Prints the list of matching tasks.
     * @param tasks The list of matching tasks
     */
    public static void printMatchingTasks(TaskList tasks){
        System.out.println("\t Here are the matching tasks in your list:");
        for (int x = 0; x < tasks.size(); x++) {
            System.out.println("\t  " + (x + 1) + "." + tasks.get(x).toString());
        }
    }
}
