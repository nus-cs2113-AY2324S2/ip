import java.util.ArrayList;

/**
 * Main class for MsChatty chatbot.
 */
public class MsChatty {
    /**
     * Main method to run MsChatty chatbot.
     *
     * @param args Command-line arguments from user.
     */
    public static void main(String[] args) {
        Ui.greetUser();
        ArrayList<Task> tasks = Storage.loadTasksFromFile();
        String userCommand = Ui.getFirstCommand();
        Parser.handleCommand(userCommand, tasks);
        Storage.saveTasksToFile(tasks);
        Ui.endSession();
    }
}
