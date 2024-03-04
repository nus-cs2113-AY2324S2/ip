import java.io.IOException; // Make sure to import IOException
import java.util.ArrayList;

public class TaskManager {
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/duke.txt");
        ArrayList<Task> loadedTasks = storage.load(); // Load tasks from storage
        TaskList taskList = new TaskList(loadedTasks); // Initialize TaskList with loaded tasks
        CommandParser commandParser = new CommandParser(taskList, ui); // Initialize CommandParser with TaskList and Ui

        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.readCommand();
            if (userInput.equalsIgnoreCase("bye")) {
                ui.showGoodbye();
                try {
                    storage.save(taskList.getTasks()); // Attempt to save tasks before exiting
                } catch (IOException e) { // Correctly handle the IOException
                    ui.showError("An error occurred while saving tasks: " + e.getMessage());
                }
                isExit = true;
            } else {
                commandParser.parseCommand(userInput);
            }
        }
    }
}
