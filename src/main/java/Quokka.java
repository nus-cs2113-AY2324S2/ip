/**
 * The Quokka class represents the main class of the Quokka task management program.
 * It initializes the task list, loads tasks from a file, and starts the user interface.
 */
public class Quokka {
    private static TaskList taskList = new TaskList();
    private static final int MAX_TASKS = 100;


    public static void main(String[] args) {

        Storage.loadTasksFromFile(taskList, MAX_TASKS);
        Ui.start(taskList);

    }
}
