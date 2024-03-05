import java.io.IOException;
import java.util.ArrayList;

public class Baymax {
    public static void main(String[] args) {

        Printer.printWelcomeMessage();
        ArrayList<Task> taskArrayList = new ArrayList<>();

        // Load Tasks from File
        try {
            Storage.loadTasks(taskArrayList);
        } catch (IOException e) {
            System.out.println("ERROR OCCURRED.");
        }

        Ui.handleUserInput(taskArrayList);

        // SAVE THE FILE
        try {
            Storage.saveTasks(taskArrayList, taskArrayList.size());
        } catch (IOException e) {
            System.out.println("ERROR SAVING FILE.");
        }

        Printer.printGoodbyeMessage();
    }

}
