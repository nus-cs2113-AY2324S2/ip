package hachi.ui;

import hachi.data.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the UI interface that the user interacts with.
 *
 */

public class Ui {
    private static Scanner in;
    public Ui () {
        this.in = new Scanner(System.in);
    }

    /**
     * Prints a greeting to the user in the console
     * with the bot's name, Hachi.
     */

    public static void printGreetingMessage() {
        String logo = "._. ._.  ._____.  ._____.  ._. ._.  ._.\n"
                + "| | | |  | ._. |  |  ___|  | | | |  | |\n"
                + "| |_| |  | |_| |  | |      | |_| |  | |\n"
                + "| ._. |  | ._. |  | |___   |  _  |  | |\n"
                + "|_| |_|  |_| |_|  |_____|  |_| |_|  |_|\n";

        System.out.println("Hey, Hachi Here!\n" + logo + "How can I assist you today?\n");
        spacerInsert("medium", false);
    }

    /**
     * Prints to the console a help message that details the commands
     * the user can use for interacting with the chatbot.
     *
     */

    public static void printHelpMessage() {
        spacerInsert("medium", true);
        System.out.println("You can use the following commands:");
        System.out.println("\t'list' to retrieve your current list of tasks,");
        System.out.println("\t'mark <#>' to mark task number # as complete,");
        System.out.println("\t'unmark <#>' to mark task number # as incomplete,");
        System.out.println("\t'todo <task name>' to create a to-do,");
        System.out.println("\t'deadline <task name> /by <by date>' to create a task with a deadline,");
        System.out.println("\t'event <task name> /from <start> /to <end>' to create an event " +
                "with a start and end date,");
        System.out.println("\t'bye' to stop chatting :('");
        System.out.println("\tAnd if you need to see this again, type 'help'!");
        spacerInsert("medium", true);
    }

    /**
     * Prints to the console a spacer line made of tildes.
     * Function call has option to choose length of the spacer,
     * as well as whether there is a 4-space indent before the spacer.
     *
     * @param length The desired length of the spacer line. Medium is chosen by default.
     * @param hasTab Whether the spacer line has a 4-space indent.
     */

    public static void spacerInsert(String length, boolean hasTab) {
        String spacer;

        if (hasTab) {
            System.out.print("\t");
        }

        switch (length) {
        case "small": // 20 tildes
            spacer = "~~~~~~~~~~~~~~~~~~~~";
            break;
        case "medium": // 40 tildes
        default:
            spacer = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
            break;
        case "large": // 60 tildes
            spacer = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
            break;
        }

        System.out.println(spacer);
    }

    public static void printTaskDeleteMessage(String taskType, String statusIcon, Task taskToDelete) {
        System.out.println("\tSure, I've done as you requested.");
        System.out.print("\t[" + taskType + "] ");
        System.out.print("[" + statusIcon + "] " + taskToDelete.getName());
        System.out.println(" has been deleted from your list.");
    }

    /**
     * Prints to the console a goodbye message for the user.
     */

    public static void printGoodbyeMessage() {
        System.out.println("\tGoodbye! Hope you have a marvelous day.");
    }

    public static String getUserInput() {
        return in.nextLine();
    }

    public static String cleanUserInput(String input) {
        return input.toUpperCase().trim();
    }

    public static void printAfterMarkOrUnmark (ArrayList<Task> tasksArrayList, int index) {
        Task currentTask = tasksArrayList.get(index);
        String taskType = currentTask.getTaskType();
        String statusIcon = currentTask.getStatusIcon();

        System.out.println("\tSure, I've done as you requested:");
        System.out.print("\t" + (index + 1) + ": ");
        System.out.print("[" + taskType + "] ");
        System.out.print("[" + statusIcon + "] ");
        System.out.println(currentTask.getName());
    }

    public static void printAddTaskMessage(Task toAdd) {
        System.out.println("\tAdded to list: " + toAdd.getName());
    }

    public static void printTaskList(ArrayList<Task> tasksArrayList) {
        System.out.println("\tThe following are in your list:");
        tasksArrayList.forEach(task -> {
            String taskType = task.getTaskType();
            String statusIcon = task.getStatusIcon();
            int currentIndex = tasksArrayList.indexOf(task);
            System.out.print("\t" + (currentIndex + 1) + ": ");
            System.out.print("[" + taskType + "] ");
            System.out.print("[" + statusIcon + "] ");
            System.out.println(task.getName());
        });
    }
}
