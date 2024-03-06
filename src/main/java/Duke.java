import java.util.ArrayList;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<>()); // initialize with an empty list
        }
    }

    public void run() {
        ui.showWelcome("Rose");
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.readCommand();
            try {
                isExit = Parser.parseAndExecute(userInput, tasks, ui, storage); // Assuming parseAndExecute returns a boolean to indicate exit
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.closeScanner(); // Close the scanner when the program ends
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run(); // Assuming the file path to the data file
    }
}

