import java.util.Scanner;
/**
 * Represents the main class of the Dul application. Handles user interaction and manages tasks.
 */
public class Dul {

    private static final String filepath = "./data/dul.txt";
    private final Ui ui;
    private final Storage storage;
    private TaskList taskList;

    public Dul(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            this.taskList = new TaskList(storage.loadTasks());
        } catch (DulException e) {
            ui.showError(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            userInput = scanner.nextLine();
            Parser.parseInput(userInput, taskList, ui);
            try {
                storage.saveTasks(taskList.getTasks());
            } catch (DulException e) {
                ui.showError(e.getMessage());
            }
        } while (!userInput.equals("bye"));

        scanner.close();
    }

    public static void main(String[] args) {
        new Dul(filepath).run();
    }
}

