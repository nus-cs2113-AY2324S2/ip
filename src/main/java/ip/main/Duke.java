package ip.main;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private static Storage storage;
    private static Ui ui = new Ui();
    private static Parser parser = new Parser();
    private static TaskList tasks = new TaskList(ui);

    public static void main(String[] args) {
        ui.introduce();
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

        readInputAndExecute();
    }

    private static void readInputAndExecute() {
        String line;
        while (true) {
            line = ui.getInput();
            boolean shouldUpdate = parser.parseInput(line, ui, tasks);
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
