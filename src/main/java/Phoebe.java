import java.util.List;
import java.util.Scanner;

public class Phoebe {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;



    public Phoebe(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(); // Initialize your TaskList
        try {
            List<Task> loadedTasks = Storage.loadTasks(); // Assume this returns List<Task>
            for (Task task : loadedTasks) {
                TaskList.addTask(String.valueOf(task)); // Assume this method exists and adds a Task to your TaskList
            }
        } catch (PhoebeException e) {
            Ui.printError();
            // Handle the error, perhaps by initializing tasks to an empty TaskList or similar
        }
    }


    public void run() {
        Ui.printGreeting();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            String input = scanner.nextLine();
            // Updated to pass tasks and ui instances to parseCommand
            isRunning = Parser.parseCommand(input, tasks, ui);
        }
        try {
            Storage.saveTasks(TaskList.displayTasks()); // Ensure this method exists and works correctly
        } catch (PhoebeException e) {
            Ui.printError();
        }
    }


    public static void main(String[] args) {
        new Phoebe("data/phoebe.txt").run();
    }
}






















