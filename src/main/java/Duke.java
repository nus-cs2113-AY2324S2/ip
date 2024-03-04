/**
 * Represents the main class of the Duke application.
 */
public class Duke {
    private static TaskList taskList;
    private static Ui ui;

    /**
     * The main method of the Duke application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        ui = new Ui();
        ui.greetUser();
        taskList = new TaskList();

        // Read user input
        while (true) {
            String userInput = ui.getUserInput();

            try {
                // Parse user input and execute corresponding command
                Parser.parseInput(userInput, taskList, ui);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
