import java.util.Scanner;
import java.io.FileNotFoundException;


/**
 * Main class for the Floda application.
 */
public class Floda {
    private static final String NAME = "Floda";
    public static final String FILE_PATH = "./data/tasks.txt";
    private static final Ui ui = new Ui();
    private static final Storage storage = new Storage(FILE_PATH);
    private static final TaskList tasks = new TaskList(storage);

    /**
     * Main method to run the Floda application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        ui.showWelcomeMessage(NAME);
        try {
            tasks.loadTasks();
        } catch (FileNotFoundException | InvalidInputException e) {
            ui.showErrorMessage(e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        ui.showInstructions();
        while (TaskList.isActive) {
            try {
                String line = scanner.nextLine().trim();
                Parser.parseCommand(line);
            } catch (InvalidInputException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
        scanner.close();
    }
}
