package geepee.system;

import java.util.Scanner;
import geepee.task.list.List;
import geepee.exceptions.InvalidCommandException;

public class UI {

    /** Scanner that reads from the terminal */
    private static Scanner in = new Scanner(System.in);

    /**
     * Returns the next line of user input.
     */
    private static String getUserInput() {
        return in.nextLine().trim();
    }

    /**
     * Initialises a loop to continuously receive user input until exit command is given.
     * 
     * @param list List to store tasks added by the user.
     */
    public static void initialiseLoop(List list) {
        String line = "";
        while (!line.equals("bye")) {
            try {
                line = getUserInput();
                InputHandler.handleUserInput(list, line);
                list.writeTasksToFile();
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
