
import java.io.IOException;


public class Bart {
    private static final String LINE = "____________________________________________________________";

    private static Storage storage;
    private static Ui ui = new Ui();
    private static final String FILE_PATH = "./data/Bart.txt";
    private static final TaskList tasksList = new TaskList();

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
        storage.saveTasks();
        ui.byeUser();
    }

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
                System.out.println(LINE + "\nERROR! Needs number input after: " + command + "\n" + LINE);
            }
        }
    }
}