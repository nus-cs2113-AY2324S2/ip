import java.util.ArrayList;
import java.util.Scanner;

/**
 * The UI class handles interaction with the user, including displaying messages,
 * reading user input, and showing the list of available commands.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";

    private static final String LOGO = "                           z$$$$P\n" +
            "                           d$$$$\"\n" +
            "                         .$$$$$\"\n" +
            "                         z$$$$$\"\n" +
            "                        z$$$$P\n" +
            "                      d$$$$$$$$$$\"\n" +
            "                     *******$$$\"\n" +
            "                          .$$$\"\n" +
            "                          .$$\"\n" +
            "                         4$P\"\n" +
            "                        z$\"\n" +
            "                        zP\n" +
            "                       z\"\n" +
            "                      / \n" +
            "                     ^ \n" +
            "      ___           ___                                    ___     \n" +
            "     /__/\\         /  /\\          ___        ___          /  /\\    \n" +
            "    |  |::\\       /  /::\\        /__/\\      /  /\\        /  /:/_   \n" +
            "    |  |:|:\\     /  /:/\\:\\       \\  \\:\\    /  /:/       /  /:/ /\\  \n" +
            "  __|__|:|\\:\\   /  /:/~/::\\       \\  \\:\\  /__/::\\      /  /:/ /::\\ \n" +
            " /__/::::| \\:\\ /__/:/ /:/\\:\\  ___  \\__\\:\\ \\__\\/\\:\\__  /__/:/ /:/\\:\\\n" +
            " \\  \\:\\~~\\__\\/ \\  \\:\\/:/__\\/ /__/\\ |  |:|    \\  \\:\\/\\ \\  \\:\\/:/~/:/\n" +
            "  \\  \\:\\        \\  \\::/      \\  \\:\\|  |:|     \\__\\::/  \\  \\::/ /:/ \n" +
            "   \\  \\:\\        \\  \\:\\       \\  \\:\\__|:|     /__/:/    \\__\\/ /:/  \n" +
            "    \\  \\:\\        \\  \\:\\       \\__\\::::/      \\__\\/       /__/:/   \n" +
            "     \\__\\/         \\__\\/           ~~~~                   \\__\\/    ";

    /**
     * Displays a greeting message when the program starts.
     */
    public void greetUser() {
        System.out.println("Greetings.\n" + LOGO);
        showDividerLine();
        System.out.println("Greetings. I am Mavis.");
        System.out.println("What would you command of me?\n");
        listOptions();
    }

    /**
     * Reads user input from the console.
     *
     * @return The user input as a string.
     */
    public String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Displays an error message.
     *
     * @param errorMessage The error message to display.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Displays an error message related to loading tasks from a file.
     *
     * @param e The exception that occurred.
     */
    public void showLoadingError(Exception e) {
        System.err.println("Error saving tasks to file: " + e.getMessage());
    }

    /**
     * Displays the list of available commands, and how to use them.
     */
    static void listOptions() {
        System.out.println("┌───────────────────────────────────────────────┐");
        System.out.println("│ Please enter a recognized command from the    │");
        System.out.println("│ following list to maintain temporal coherence:│");
        System.out.println("│                                               │");
        System.out.println("│  1. todo <task name>                          │");
        System.out.println("│  2. deadline <task name> /by:<date/time>      │");
        System.out.println("│  3. event <task name> /from:<date> /to:<date> │");
        System.out.println("│  4. mark <task number>                        │");
        System.out.println("│  5. unmark <task number>                      │");
        System.out.println("│  6. list                                      │");
        System.out.println("│  7. find <description keywords>               │");
        System.out.println("│  8. delete <task number>                      │");
        System.out.println("│  9. bye                                       │");
        System.out.println("└───────────────────────────────────────────────┘");
    }

    /**
     * Displays the current total list of tasks.
     *
     * @param listOfTasks The ArrayList containing the tasks to display.
     * @param size        The total number of tasks in the list.
     */
    static void printTasks(ArrayList<Task> listOfTasks, int size) {
        showDividerLine();
        System.out.println("Herein lies the catalog of your labors: ");
        for (int i = 0; i < size; i++) {
            listTask(i, listOfTasks.get(i));
        }
        showDividerLine();
    }

    /**
     * Displays a single task.
     *
     * @param currentTaskIndex The index of the task.
     * @param currentTask      The task to display.
     */
    public static void listTask(int currentTaskIndex, Task currentTask) {
        System.out.println("[" + currentTask.taskType + "]" + "[" + currentTask.getStatusIcon() + "] " + (currentTaskIndex + 1) + ". " + currentTask.description);
    }

    /**
     * Displays a newly added task.
     *
     * @param newTask               The task that was added.
     * @param currentNumberOfTasks  The current number of tasks.
     */
    static void showNewlyAddedTask(Task newTask, int currentNumberOfTasks) {
        showDividerLine();
        System.out.println("A new task is etched upon the sands of time:");
        System.out.println("[" + newTask.taskType + "]" + "[" + newTask.getStatusIcon() + "]" + newTask.description);
        System.out.println("Your roster now bears " + currentNumberOfTasks + " endeavors.");
        showDividerLine();
    }

    /**
     * Displays a divider line in the terminal.
     */
    public static void showDividerLine() {
        System.out.println(LINE);
    }
}
