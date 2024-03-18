package ip.main;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents the chatbot program
 */
public class Duke {
    private static Storage storage;
    private static Ui ui = new Ui();
    private static Parser parser = new Parser();
    private static TaskList tasks = new TaskList(ui);
    private static TaskListUpdater updater = new TaskListUpdater(ui);

    /**
     * The entry point of the application.
     * The bot loads previously stored data and introduces itself
     * before the user can start giving instructions
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try {
            storage = new Storage("./data/task_list.txt", ui);
        } catch (IOException e) {
            ui.printWithoutLeadingSpace("Unable to create data file!");
            return;
        }

        try {
            storage.readStoredData(tasks);
        } catch (FileNotFoundException e) {
            ui.printWithoutLeadingSpace("File not found!");
            return;
        }

        ui.introduce();
        readInputAndExecute();
    }

    /**
     * Repeatedly reads in the user's input and execute the commands
     * given, updating the data file when needed.
     * Stops when the user says "bye"
     */
    private static void readInputAndExecute() {
        String line;
        while (true) {
            line = ui.getInput();
            boolean shouldUpdate = parser.parseInput(line, ui, tasks, updater);
            if (shouldUpdate) {
                storage.updateStoredData(tasks);
            }
            if (parser.getEnded()) {
                ui.printWithoutLeadingSpace("Bye. Hope to see you again soon!");
                break;
            }
        }
    }
}
