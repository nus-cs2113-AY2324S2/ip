package geepee.system;

import java.util.Scanner;
import geepee.task.list.List;
import geepee.exceptions.InvalidCommandException;

public abstract class UI {

    private static Scanner in = new Scanner(System.in);

    private static String getUserInput() {
        return in.nextLine().trim();
    }

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
