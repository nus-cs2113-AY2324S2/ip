package hachi.ui;

import hachi.data.HachiException;
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
        in = new Scanner(System.in);
    }

    /**
     * Prints a greeting to the user in the console
     * with the bot's name, Hachi.
     */

    public void printGreetingMessage() {
        String logo = "._. ._.  ._____.  ._____.  ._. ._.  ._.\n"
                + "| | | |  | ._. |  |  ___|  | | | |  | |\n"
                + "| |_| |  | |_| |  | |      | |_| |  | |\n"
                + "| ._. |  | ._. |  | |___   |  _  |  | |\n"
                + "|_| |_|  |_| |_|  |_____|  |_| |_|  |_|\n";

        System.out.println("Hey, Hachi Here!\n" + logo + "How can I assist you today?\n");
        spacerInsert();
    }

    /**
     * Prints to the console a help message that details the commands
     * the user can use for interacting with the chatbot.
     *
     */

    public void printHelpMessage() {
        spacerInsert();
        System.out.println("You can use the following commands:");
        System.out.println("\t'list' to retrieve your current list of tasks,");
        System.out.println("\t'mark <#>' to mark task number # as complete,");
        System.out.println("\t'unmark <#>' to mark task number # as incomplete,");
        System.out.println("\t'todo <task name>' to create a to-do,");
        System.out.println("\t'deadline <task name> /by <by date>' to create a task with a deadline,");
        System.out.println("\t'event <task name> /from <start> /to <end>' to create an event " +
                "with a start and end date,");
        System.out.println("\t'delete <task number> to delete a previously created entry,");
        System.out.println("\t'bye' to stop chatting :('");
        System.out.println("\tAnd if you need to see this again, type 'help'!");
        spacerInsert();
    }

    /**
     * Prints to the console a spacer line made of tildes.
     * Function call has option to choose length of the spacer,
     * as well as whether there is a 4-space indent before the spacer.
     */

    public void spacerInsert() {
        System.out.print("\t");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void printTaskDeleteMessage(String taskType, String statusIcon, Task taskToDelete) {
        System.out.println("\tSure, I've done as you requested.");
        System.out.print("\t[" + taskType + "] ");
        System.out.print("[" + statusIcon + "] " + taskToDelete.getName());
        System.out.println(" has been deleted from your list.");
    }

    /**
     * Prints to the console a goodbye message for the user.
     */

    public void printGoodbyeMessage() {
        System.out.println("\tGoodbye! Hope you have a marvelous day.");
    }

    public String getUserInput() {
        return in.nextLine();
    }

    public String cleanUserInput(String input) {
        return input.toUpperCase().trim();
    }

    public void printAfterMarkOrUnmark (ArrayList<Task> tasksArrayList, int index) {
        Task currentTask = tasksArrayList.get(index);
        String taskType = currentTask.getTaskType();
        String statusIcon = currentTask.getStatusIcon();

        System.out.println("\tSure, I've done as you requested:");
        System.out.print("\t" + (index + 1) + ": ");
        System.out.print("[" + taskType + "] ");
        System.out.print("[" + statusIcon + "] ");
        System.out.println(currentTask.getName());
    }

    public void printAddTaskMessage(Task toAdd) {
        System.out.println("\tAdded to list: " + toAdd.getName());
    }

    public void printTaskList(ArrayList<Task> tasksArrayList) {
        System.out.println("\tThe following are in your list:");
        printFromTasksList(tasksArrayList);
    }

    public void printFileLoadingError () {
        System.out.println("There was an error finding save files. Creating a new save...");
    }

    public void printFoundTasks (ArrayList<Task> foundTasksList) throws HachiException {
        HachiException.checkForEmptyFoundTaskList(foundTasksList);
        System.out.println("I found these matching tasks in your task list: ");
        printFromTasksList(foundTasksList);
    }

    private void printFromTasksList(ArrayList<Task> tasksList) {
        tasksList.forEach(task -> {
            String taskType = task.getTaskType();
            String statusIcon = task.getStatusIcon();
            int currentIndex = tasksList.indexOf(task);
            System.out.print("\t" + (currentIndex + 1) + ": ");
            System.out.print("[" + taskType + "] ");
            System.out.print("[" + statusIcon + "] ");
            System.out.println(task.getName());
        });
    }
}
