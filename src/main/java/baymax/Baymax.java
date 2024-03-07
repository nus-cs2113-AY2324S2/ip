package baymax;

import java.io.IOException;
import java.util.ArrayList;

public class Baymax {
    public static void main(String[] args) {

        Printer.printWelcomeMessage();
        ArrayList<Task> tasks = new ArrayList<>();

        // Load Tasks from File
        try {
            Storage.loadTasks(tasks);
        } catch (IOException e) {
            System.out.println("ERROR OCCURRED.");
        }

        Ui.handleUserInput(tasks);

        // SAVE THE FILE
        try {
            Storage.saveTasks(tasks);
        } catch (IOException e) {
            System.out.println("ERROR SAVING FILE.");
        }

        Printer.printGoodbyeMessage();
    }

}
