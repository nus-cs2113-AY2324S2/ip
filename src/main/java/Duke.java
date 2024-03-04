public class Duke {
    private static TaskList taskList;
    private static Ui ui;

    public static void main(String[] args) {
        ui = new Ui();
        ui.greetUser();
        taskList = new TaskList();

        // Read user input
        while (true) {
            String userInput = ui.getUserInput();

            try {
                Parser.parseInput(userInput, taskList, ui);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}

