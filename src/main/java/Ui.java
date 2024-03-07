import jason.errorhandling.JasonException;
import jason.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles user interface interactions for the application.
 * This includes displaying welcome messages, reading commands, showing errors, and listing tasks.
 */
public class Ui {
    private static final Scanner scanner = new Scanner(System.in);

    public static void showWelcome() {
        System.out.println("   J     A     SSSS    OOO   N   N \n"
                + "   J    A A    S      O   O  NN  N \n"
                + "   J   A A A    SSS   O   O  N N N \n"
                + "J  J  A     A      S  O   O  N  NN \n"
                + " JJJ A       A  SSSS   OOO   N   N \n"
                + "Eyy wassup I'm Jason\nWhat can I do for you?\n");
    }

    public static String readCommand() {
        return scanner.nextLine();
    }

    public static void showGoodbye() {
        System.out.println("Bye. See ya later!\n____________________________________________________________\n");
    }

    public static void showError(String message) {
        System.out.println("Error: " + message);
    }

    public static void showLine() {
        System.out.println("____________________________________________________________");
    }


    /**
     * Displays the list of tasks to the user.
     *
     * @param list The list of tasks to be displayed.
     * @throws JasonException if an error occurs during display.
     */
    public static void showList(ArrayList<Task> list) throws JasonException {
        if (list.isEmpty()) {
            System.out.println("The task list is currently empty. Add some tasks!");
            return;
        }

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ":" + list.get(i));
        }
    }

    public static void showFoundTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks match your keyword.");
        } else {
            System.out.println("Tasks that match your keyword:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }


    /**
     * Displays the number of tasks in the list to the user.
     *
     * @param list The list of tasks.
     */
    public static void showTaskNumber(ArrayList<Task> list) {
        if (list.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        }
    }
}