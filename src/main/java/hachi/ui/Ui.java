package hachi.ui;

import hachi.data.HachiException;
import hachi.data.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the UI interface that the user interacts with for the chatbot Hachi.
 *
 * @author clarencepohh
 * @version 08/03/2024
 */

public class Ui {
    private static Scanner in;

    /**
     * The standard constructor for the Ui class.
     * Initializes the required Scanner for the Ui class.
     *
     */

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
        System.out.println("\t'find <substring> to find tasks in your list that have the specified substring,");
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
     *
     */

    public void spacerInsert() {
        System.out.print("\t");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    /**
     * Prints to the console the message after a Task is deleted from the TaskList.
     *
     * @param taskType The TaskType of the Task to be deleted.
     * @param statusIcon The String that contains the statusIcon: T (for Todo), D (for Deadline), E (for Event)
     * @param taskToDelete The Task to be deleted from the TaskList.
     */

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

    /**
     * Gets the user input from the console.
     *
     * @return The String that contains the latest user input.
     */

    public String getUserInput() {
        return in.nextLine();
    }

    /**
     * Converts the user input to uppercase and removes any trailing whitespace.
     *
     * @param input The String that contains the whole user input.
     * @return The cleaned whole user input.
     */

    public String cleanUserInput(String input) {
        return input.toUpperCase().trim();
    }

    /**
     * Prints to the console the message after a Task is marked/unmarked
     * in the TaskList.
     *
     * @param tasksArrayList The ArrayList<Task> to be searched for marking/unmarking.
     * @param index The int containing the Task index to be marked/unmarked.
     */

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

    /**
     * Prints to the console after a Task has been added to the TaskList.
     *
     * @param toAdd The Task to be added to the TaskList.
     */

    public void printAddTaskMessage(Task toAdd) {
        System.out.println("\tAdded to list: " + toAdd.getName());
    }

    /**
     * Prints to the console all the Tasks in the TaskList.
     *
     * @param tasksArrayList The ArrayList<Task> to be printed.
     */

    public void printTaskList(ArrayList<Task> tasksArrayList) {
        System.out.println("\tThe following are in your list:");
        printFromTasksList(tasksArrayList);
    }

    /**
     * Prints to the console an error message if there was an error loading the save file.
     *
     */

    public void printFileLoadingError () {
        System.out.println("There was an error finding save files. Creating a new save...");
    }

    /**
     * Function is called from a find request.
     * Given the ArrayList<Task> containing the Tasks found after a find request,
     * prints to the console all the Tasks found.
     *
     * @param foundTasksList The ArrayList<Task> containing the found tasks.
     * @throws HachiException If foundTasksList is empty.
     */

    public void printFoundTasks (ArrayList<Task> foundTasksList) throws HachiException {
        HachiException.checkForEmptyFoundTaskList(foundTasksList);
        System.out.println("I found these matching tasks in your task list: ");
        printFromTasksList(foundTasksList);
    }

    /**
     * Given an ArrayList<Task>, prints to the console all the Tasks in the ArrayList.
     *
     * @param tasksList The ArrayList<Task> to be printed.
     */

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
