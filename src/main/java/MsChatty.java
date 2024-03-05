import java.util.ArrayList;

public class MsChatty {
    public static void main(String[] args) {
        Ui.greetUser();
        ArrayList<Task> tasks = Storage.loadTasksFromFile();
        String userCommand = Ui.getFirstCommand();
        Parser.handleCommand(userCommand, tasks);
        Storage.saveTasksToFile(tasks);
        Ui.endSession();
    }
}
