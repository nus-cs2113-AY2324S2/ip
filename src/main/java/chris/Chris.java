package chris;

import chris.commands.Command;
import chris.customexceptions.*;
import chris.tasktypes.taskList;

public class Chris {
    protected static Storage storage;
    protected static taskList tasks;
    protected static UI ui;
    protected static Parser parser;

    public Chris(String filePath) {
        storage = new Storage(filePath);
        tasks = storage.loadTasks();
        ui = new UI();
        parser = new Parser();

    }
    public static void main(String[] args) {
        new Chris("data.txt").run();
    }

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
