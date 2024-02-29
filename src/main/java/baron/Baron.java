package baron;

import baronException.BaronException;
import storage.FileStorage;
import task.TaskList;
import parser.Parser;

import java.io.IOException;
import java.util.Scanner;

/**
 * The Baron class is the main class that runs the program.
 */

public class Baron {
    public static void main(String[] args) throws IllegalArgumentException {
        greetUser();

        TaskList tasks = new TaskList();

        FileStorage.loadFile();

        try {
            getUserInput(tasks);
        } catch (IllegalArgumentException e) {
            System.out.println("Command cannot be empty. Please enter a valid command.");
        } catch (IOException | BaronException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    /**
     * Prints a simple message to greet the user.
     */

    private static void greetUser() {
        System.out.println("Hello! I'm Baron");
        System.out.println("What can I do for you?\n");
    }

    /**
     * Allows the user to type commands to Baron.
     * @param tasks the current task list
     * @throws IOException when there is an error with reading and writing to the file
     * @throws BaronException when there is an error with the format of the user input
     */

    public static void getUserInput(TaskList tasks) throws IOException, BaronException {
        Scanner userInput = new Scanner(System.in);

        //noinspection InfiniteLoopStatement
        while (true) {
            String input = userInput.nextLine();
            input = input.toLowerCase();
            Parser.parseInput(input, tasks);
        }
    }

    /**
     * Prints a simple message to bid farewell to the user.
     */

    public static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
