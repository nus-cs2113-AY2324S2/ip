package nyanbot;

import java.util.ArrayList;
import nyanbot.task.Task;
import nyanbot.tool.TaskList;
import nyanbot.tool.UI;
import nyanbot.tool.Storage;
import nyanbot.tool.Parser;

/***
 * NyanBot is a simple task management CLI app
 * Users can store different task types with varying descriptions and time-related info
 * Users can view, remove, add and find tasks
 * Data is stored automatically in ./data/nyan.txt file
 */
public class NyanBot {
    public static final String DATA_DIRECTORY = "./data";
    private Storage storage;
    private TaskList tasks;
    private Boolean isRunning = true;

    private NyanBot(String directory) {
        storage = new Storage(directory);
        tasks = new TaskList();
        initialiseNyan();
        runNyan();
    }

    private void initialiseNyan() {
        ArrayList<Task> importedTasks = storage.readFile();
        tasks.importTask(importedTasks);
        UI.printGreet();
    }

    private void runNyan() {
        while (isRunning) {
            String input = UI.getCommand();
            Parser.executeCommand(input, tasks);
            isRunning = Parser.getStatus();
            writeNyan();
        }
        UI.printBye();
    }

    private void writeNyan() {
        ArrayList<Task> tasks = this.tasks.exportTask();
        storage.writeFile(tasks);
    }

     public static void main(String[] args) {
        new NyanBot(DATA_DIRECTORY);
    }
}
