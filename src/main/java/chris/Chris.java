package chris;

import chris.commands.Command;
import chris.customexceptions.*;
import chris.tasktypes.taskList;

public class Chris {
    private static Storage storage;
    private static taskList tasks;
    private static UI ui;
    private static Parser parser;

    public Chris(String filePath) {
        storage = new Storage(filePath);
        tasks = storage.loadTasks();
        ui = new UI();
        parser = new Parser();

    }
    public static void main(String[] args) {
        new Chris("data.txt").run();
    }

    /**
     * Core logic of the chatbot Chris
     */
    public void run() {
        ui.printWelcome();
        boolean done = false;
        while (!done) {
            String input = ui.readInput();
            try {
                Command command = parser.parse(input);
                String result = command.execute(tasks);
                ui.printMessage(result);
                done = command.quit();
            } catch (customExceptions e) {
                ui.printException(e);
            }
        }
        storage.saveTasks(tasks);
    }
}
