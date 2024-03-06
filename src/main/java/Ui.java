import java.util.ArrayList;

/**
 * Handles user interface interactions, including displaying messages and alerts to the console.
 * The UI interactions is based off of a special character in my life that may or may not have said such messages to me irl XD
 */

public class Ui {

    /**
     * Prints a greeting message when the application starts that includes a block name of Phoebe.
     */
    public static void printGreeting() {
        String greet = "\n" +
                "█▀█ █░█ █▀█ █▀▀ █▄▄ █▀▀\n" +
                "█▀▀ █▀█ █▄█ ██▄ █▄█ ██▄\n" + "HELLOOOO WHATCHA DOING???????";
        System.out.println(greet);
    }

    /**
     * Prints an exit message when the application is closing and Phoebe goes to snoozeland.
     */
    public static void printExit() {
        String exit = "byebye\n" + "ฅ^•ﻌ•^ฅ";
        System.out.println(exit);
    }

    /**
     * Informs the user that no saved tasks were found, indicating a fresh start.
     */
    public static void printFileNotFound() {
        System.out.println("I CANT RMB ANYTHING U TOLD ME nvm i start new mmrs");
    }

    /**
     * Warns the user that there was an error loading tasks, suggesting possible file corruption.
     */
    public static void printCorrupted() {
        System.out.println("Error loading tasks. Data file might be corrupted.");
    }

    /**
     * Indicates a problem parsing a user command.
     */
    public static void printParseError() {
        System.out.println("what did u even say");}

    /**
     * Displays a message to the user when the description for a todo task is not specified.
     */
    public static void printTodoNotSpecified() {
        System.out.println("hello fool how can you todo nothing??????");
    }

    /**
     * Displays a message when an event task is added without specifying details.
     */
    public static void printEventNotSpecified() {
        System.out.println("you tell me empty event for what");
    }

    /**
     * Informs the user that the event timing is unclear due to missing '/from' and '/to' specifications.
     */
    public static void printEventUnclear() {
        System.out.println("event no (/from and /to)??? then what time u can go home?");
    }

    public static void printDeadlineNotSpecified() {
        System.out.println("if never tell me /by how I know the deadline??? im ded.");
    }

    /**
     * Informs the user that the input is incomprehensible beyond belief and phoebe is questioning itself.
     */
    public static void printUserIsStupid() {
        System.out.println("you dont make any sense");
    }

    /**
     * Prints a message indicating that the task has been successfully added to the task list.
     *
     * @param newTask The task that was added.
     * @param size    The new size of the task list.
     */
    public static void printTaskAdded(Task newTask, int size) {
        System.out.println("OKIE I MEMORISED FOR U:\n  " + newTask);
        System.out.println("You have " + size + " remaining things to dododo.");
    }

    /**
     * Prints a message indicating that a task has been successfully deleted from the task list.
     *
     * @param removedTask The task that was removed.
     * @param size        The new size of the task list.
     */
    public static void printTaskDeleted(Task removedTask, int size) {
        System.out.println("Just now you said do but now say don't, so I forgot this:\n  " + removedTask);
        System.out.println("Now you have " + size + " remaining things to dododo.");
    }

    /**
     * Informs the user that an attempt to delete a task failed because the task could not be found.
     */
    public static void printDeleteTaskNotFound() {
        System.out.println("Which one is that? You never tell me this before:");
    }

    public static void printDeleteTaskError() {
        System.out.println("Eh, use numbers to tell me which one to forget, can?");
    }

    public static void printDisplayEmptyTasks() {
        System.out.println("U never tell me anything how I know");
    }

    public static void printDisplayTasks() {
        System.out.println("Every time need me to remind you...");
    }

    public static void printMarkUnmarkMissingTask() {
        System.out.println("u stupid u never tell me this before:");
    }

    public static void printMarkDoneTask(Task tasks) {
        System.out.println("YAY GOOD JOB\n  " + tasks);
    }

    public static void printMarkUndoneTask(Task tasks) {
        System.out.println("Just now say do alr now never do\n  " + tasks);
    }

    /**
     * Informs the user that an error occurred while trying to save tasks.
     */
    public static void printErrorSaving() {
        System.out.println("MY BRAIN FRIED I CANT RMB ANYTHING ANYMORE.");
    }

    /**
     * Displays an error message indicating a generic error occurred.
     */
    public static void printError() { System.out.println("Phoebe mightve died cos error occurred");}

    /**
     * Informs the user that no tasks matching their query were found.
     */
    public static void printNoMatchingTasks() {
        System.out.println("nothing matches what you told me.");
    }

    /**
     * Displays the tasks that match the user's search query.
     *
     * @param matchingTasks An ArrayList of tasks that match the query.
     */
    public static void printMatchingTasks(ArrayList<Task> matchingTasks) {
        System.out.println("OK THESE ARE THE ONES THAT AGARAGAR match waht u want:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println((i + 1) + "." + matchingTasks.get(i));
        }
    }

    /**
     * Informs the user about the correct format to use when specifying a deadline.
     */
    public static void printDeadlineFormat() {
        System.out.println("you dunno how to type date isit. please use yyyy-MM-dd HHmm, e.g., 2019-12-02 1800.");
    }
}
