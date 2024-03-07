package baymax;

import java.util.ArrayList;

public class Baymax {
    public static void main(String[] args) {

        Printer.printWelcomeMessage();
        ArrayList<Task> tasks = new ArrayList<>();

        // Load Tasks from File
        Storage.handleLoadTasks(tasks);

        // Handles user's input and replies accordingly
        Ui.handleUserInput(tasks);

        // Saves existing tasks into baymax.txt
        Storage.handleSaveTasks(tasks);

        Printer.printGoodbyeMessage();
    }

}
