package HikoUi;


import java.util.ArrayList;
import java.util.Scanner;
import Exceptions.HikoExceptions;
import Events.Task;
import Events.TaskList;
import Storage.Store;
import HikoUi.Ui;



public class Ui {

    static final String break_line = "----------------------------------------";
    private final Scanner sc;

    /**
     * Constructs a new Ui instance, initializing the scanner for reading user input.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The user's input line as a String.
     */
    public String UserInput() {
        return sc.nextLine();
    }


    /**
     * Displays the Hiko application logo and a welcome message.
     */
    public static void showHiko() {
        String logo =
                "  \n" +
                        " __ __  ____  __  _   ___  \n" +
                        "|  |  ||    ||  |/ ] /   \\ \n" +
                        "|  |  | |  | |  ' / |     |\n" +
                        "|  _  | |  | |    \\ |  O  |\n" +
                        "|  |  | |  | |     \\|     |\n" +
                        "|  |  | |  | |  .  ||     |\n" +
                        "|__|__||____||__|\\_| \\___/ \n" +
                        "                            ";
        System.out.println("Hello from  \n" + logo);
    }


    /**
     * Prints a greeting message to the user.
     */
    public static void sayhi() {
        System.out.println("How can Hiko help you today?");
    }

    /**
     * Displays a hint message suggesting the user type 'help' for a list of commands.
     */
    public static void hint() {
        System.out.println("Type 'help' for list of instructions.");
    }

    /**
     * Prints a divider line to the console.
     */
    public static void divider() {
        System.out.println(break_line);
    }

    /**
     * Displays a help message listing all available commands and their descriptions.
     */

    public static void helpmsg() {
        Ui.divider();
        System.out.println("'bye' to exit ");
        System.out.println("'list' to display the list");
        System.out.println("'clearlist' to clear the list ");
        System.out.println("'mark' plus a number to mark that task as done");
        System.out.println("'unmark' plus a number to unmark that task.");
        System.out.println("'help' for list of instructions.");
        System.out.println("'todo [description]' to add a new Events.ToDo task");
        System.out.println("'deadline [description] /by [time]' to add a new Events.Deadline");
        System.out.println("'event [description] /from [start time] /to [end time]' to add a new Events.Event");
        System.out.println("'delete' plus a number to delete that task.");
        Ui.divider();
    }
    /**
     * Displays a default message when an unknown command is entered.
     */
    public static void defaultmsg() {
        Ui.divider();
        System.out.println("Sorry, Hiko doesn't understand that command.");
        System.out.println("Type 'help' for a list of valid commands.");
        Ui.divider();
    }

    /**
     * Displays a goodbye message to the user and exits the application.
     */

    public static void sayGoodbye() {
        System.out.println("Thank you for using our task manager. Goodbye!");
        System.exit(0);
    }

    /**
     * Shows the list of tasks currently in the TaskList.
     *
     * @param tasks The TaskList containing the tasks to display.
     */
    public static void showList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("There are no Tasks in Your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < TaskList.getTotalTasks(); i++) {
                System.out.println(i + 1 + "." + tasks.getTask(i).toString());
            }
        }
    }

    /**
     * Displays a list of tasks that match a search query.
     *
     * @param tasks An ArrayList of Task objects that match the search criteria.
     */
    public static void showFindList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Which task are you looking for ?");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + "." + tasks.get(i).toString());
            }
        }
    }

    /**
     * Displays a message confirming the addition of a new task.
     *
     * @param tasks The Task that was added.
     */
    public static void showAddTask(Task tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.toString());
        System.out.println("Now you have " + TaskList.getTotalTasks() + " tasks in the list.");
    }

    /**
     * Displays an error message for invalid command or number input.
     */

    public static void showErrorMsg() {
        System.out.println("I guess You have input a wrong number or command");
    }

    /**
     * Displays a message confirming the deletion of a task.
     *
     * @param tasks The Task that was removed.
     */
    public static void showDeleteTask(Task tasks) {
        System.out.println("Got it. I've removed this task:");
        System.out.println(tasks.toString());
        System.out.println("Now you have " + (TaskList.getTotalTasks() - 1) + " tasks in the list.");
    }

    /**
     * Displays a message confirming that all tasks have been cleared from the list.
     */
    public static void showClearMsg() {
        System.out.println("I have cleared all the tasks in the list ! ");

    }




}

