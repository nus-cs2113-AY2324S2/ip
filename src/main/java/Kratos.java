import java.util.ArrayList;

public class Kratos {
    private static Ui ui = new Ui();
    private static Storage storage = new Storage();

    private static TaskList listOfTasks = new TaskList();
    static ArrayList<Task> tasksList = new ArrayList<>();
    private static final String FILE_PATH = "./data/tasks.txt";



    // Main method
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
                    ui.displayTasks(tasksList.size(), tasksList);
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

    private static void handleCommand(String userInput) {
        try {
            if (userInput.startsWith("mark") || userInput.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]); //Extract task number
                listOfTasks.identifyAndMarkTasks(taskNumber - 1, userInput.split(" ")[0],tasksList);
            } else if (userInput.startsWith("deadline")) {
                listOfTasks.addDeadline(userInput,tasksList);
            } else if (userInput.startsWith("todo")) {
                listOfTasks.addTodo(userInput,tasksList);
            } else if (userInput.startsWith("event")) {
                listOfTasks.addEvent(userInput,tasksList);
            } else if (userInput.startsWith("delete")) {
                listOfTasks.deleteTask(userInput,tasksList);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            KratosException.handleException(e, userInput);
        }
    }



}
