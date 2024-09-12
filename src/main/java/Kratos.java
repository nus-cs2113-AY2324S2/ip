import java.util.ArrayList;

/**
 * The main class responsible for running the Kratos task management application.
 */
public class Kratos {
    private static Ui ui = new Ui();
    private static Storage storage = new Storage();
    private static TaskList listOfTasks = new TaskList();
    static ArrayList<Task> tasksList = new ArrayList<>();

    /**
     * The main method of the Kratos application.
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {

        ui.greet();
        tasksList = storage.loadTasks(); // Load tasks from file when the program boots up
        String userInput;
        try {
            while (true) {
                userInput = ui.readCommand();
                switch (userInput) {
                case "bye":
                    ui.end();
                    return;
                case "list":
                    ui.displayTasks(ui.LIST,tasksList);
                    break;
                case "help":
                    ui.displayCommands();
                    break;
                default:
                    handleCommand(userInput);
                    break;
                }
                storage.saveTasks(tasksList);
            }
        } finally {
            ui.closeScanner();
        }
    }

    /**
     * Handles the user command input.
     *
     * @param userInput The user input command.
     */
    private static void handleCommand(String userInput) {
        try {
            if (userInput.startsWith("mark") || userInput.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(Parser.splitTaskNumber(userInput)[1]); //Extract task number
                TaskList.identifyAndMarkTasks(taskNumber - 1, Parser.splitTaskNumber(userInput)[0],tasksList);
            } else if (userInput.startsWith("deadline")) {
                TaskList.addDeadline(userInput,tasksList);
            } else if (userInput.startsWith("todo")) {
                listOfTasks.addTodo(userInput,tasksList);
            } else if (userInput.startsWith("event")) {
                listOfTasks.addEvent(userInput,tasksList);
            } else if (userInput.startsWith("delete")) {
                listOfTasks.deleteTask(userInput,tasksList);
            } else if (userInput.startsWith("find")) { // Add handling for "find" command
                String keyword = Parser.parseFindKeyword(userInput); // Extract the keyword after "find"
                TaskList.findTasks(keyword, tasksList);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            KratosException.handleException(e, userInput);
        }
    }
}
