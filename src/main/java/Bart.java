
import java.io.IOException;

/**
 * The Bart class manages tasks through various commands provided by the user.
 */
public class Bart {
    private static final String LINE = "________________________________________________________________________________";

    private static Storage storage;
    private static Ui ui = new Ui();
    private static final String FILE_PATH = "./data/Bart.txt";
    private static final TaskList tasksList = new TaskList();

    /**
     * The main initializes the program to load and manage tasks,
     * then saves tasks after taking "bye" as input.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        ui.greetUser();
        try {
            storage = new Storage(FILE_PATH);
        } catch (IOException e) {
            ui.println("Unable to create data file!");
            return;
        }
        storage.loadTasks(tasksList);
        manageTask();
        storage.saveTasks(tasksList);
        ui.byeUser();
    }

    /**
     * Manages tasks based on input "command" until input "bye".
     */
    public static void manageTask() {
        String command;

        while (true) {
            command = ui.getInput();
            try {
                if (command.equals("help")) {
                    ui.printHelp();
                } else if (command.equals("bye")) {
                    break;
                } else if (command.equals("list")) {
                    tasksList.listTasks();
                } else if (command.startsWith("mark")) {
                    tasksList.markTask(command, true);
                } else if (command.startsWith("unmark")) {
                    tasksList.markTask(command, false);
                } else if (command.startsWith("delete")) {
                    tasksList.deleteTask(command);
                } else if (command.startsWith("find")) {
                    tasksList.findTasks(command);
                } else {
                    tasksList.addNewTask(command);
                }
            } catch (IllegalArgumentException | StringIndexOutOfBoundsException e) {
                System.out.println(LINE + "\nERROR! Wrong input: " + command + "\n" + LINE);
            }
        }
    }
}