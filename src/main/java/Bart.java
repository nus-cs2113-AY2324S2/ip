import java.util.NoSuchElementException;
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

            if (command.equals("help")) {
                ui.printHelp();
            } else if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                listTasks();
            } else if (command.startsWith("mark")) {
                markTask(command, true);
            } else if (command.startsWith("unmark")) {
                markTask(command, false);
            } else if (command.startsWith("delete")) {
                tasksList.deleteTask(command);
            } else if (command.startsWith("find")) {
                try {
                    tasksList.findTasks(command);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid search input: " + command);
                }
            }
            else {
                tasksList.addNewTask(command);
            }
        }
    }

    public static void listTasks() {
        ui.println(LINE + "\nHere are the tasks in your list:");
        //Edge case: If list empty
        if (tasksList.isEmpty()) {
            ui.println("Nothing added here....");
        }
        for (int i = 0; i < tasksList.size(); i++) {
            ui.println("  "+ (i + 1) + "." + tasksList.get(i).toString());
        }
        ui.println(LINE);
    }

    public static void markTask(String command, boolean mark) {
        int taskIndex = Integer.parseInt(command.substring(command.indexOf(' ') + 1).trim()) - 1;
        if (taskIndex >= 0 && taskIndex < tasksList.size()) {
            Task task = tasksList.get(taskIndex);
            if (mark) {
                task.markAsDone();
                ui.println(LINE + "\nNice! I've marked this task as done:");
            } else {
                task.markAsUndone();
                ui.println(LINE + "\nOK, I've marked this task as not done yet:");
            }
            ui.println(task.getTaskMark() + " " + task.description + "\n" + LINE );
        } else {
            ui.println(LINE + "\nInvalid task number.\n" + LINE);
        }
    }
}