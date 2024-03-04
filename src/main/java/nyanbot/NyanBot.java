package nyanbot;

import java.util.Scanner;
import java.util.ArrayList;
import nyanbot.task.Task;
import nyanbot.tool.TaskList;
import nyanbot.tool.UI;
import nyanbot.tool.Storage;
import nyanbot.tool.Parser;

public class NyanBot {
    public static final String DATA_DIRECTORY = "./data";
    private Storage storage;
    private TaskList tasks;
    private Boolean isRunning = true;

    public NyanBot(String directory) {
        storage = new Storage(directory);
        tasks = new TaskList();
        initialiseNyan();
        runNyan();
    }

    public void initialiseNyan() {
        ArrayList<Task> importedTasks = storage.readFile();
        tasks.importTask(importedTasks);
        UI.printGreet();
    }

    public void runNyan() {
        while (isRunning) {
            String input = UI.getCommand();
            Parser.processCommand(input, tasks);
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
