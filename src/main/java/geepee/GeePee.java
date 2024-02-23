package geepee;

import java.io.File;
import geepee.system.InputHandler;
import geepee.system.SystemMessage;
import geepee.task.list.List;
import geepee.exceptions.InvalidCommandException;

public class GeePee {

    private static List list = new List("data/data.txt");

    private static void initialiseLoop() {
        new File("data/").mkdir();
        String line = "";
        while (!line.equals("bye")) {
            try {
                line = InputHandler.getUserInput();
                InputHandler.handleUserInput(list, line);
                list.updateFile();
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SystemMessage.printWelcomeMessage();
        initialiseLoop();
        SystemMessage.printExitMessage();
    }
}
