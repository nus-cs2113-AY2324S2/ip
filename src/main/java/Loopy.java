import java.util.ArrayList;
import java.util.Scanner;

public class Loopy {
    private static ArrayList<Task> tasks = new ArrayList<>();
    public static TaskList taskList = new TaskList(tasks);
    public static void main(String[] args) {
        Scanner taskScanner = new Scanner(System.in);
        Ui.showWelcomeMessage();

        Loopy.taskList= Storage.loadFile();
        Loopy.tasks = taskList.getTasks(); //loads file, then brings tasks here

        Ui.showMessage("What can I do for you?\n");
        while (true) {
            String task = taskScanner.nextLine(); //scan user input
            processTask(task);
            if (task.equals("bye")) {
                Ui.showExitMessage();
                Storage.saveFile(tasks);
                break;
            }
        }
    }
    /**
     * Processes the input command from the user.
     * Add, delete, mark, display and find tasks based on the command.
     *
     * @param task The user input command to process.
     */
    private static void processTask(String task) {
        String[] command = task.split(" ");

        switch (command[0].toLowerCase()){
            case "list":
                taskList.displayTaskList();
                break;
            case "mark":
                try {
                    TaskList.markTaskAsDone(task);
                } catch (LoopyExceptions exceptions) {
                    Ui.showMessage("Warning! " + exceptions.getMessage());
                }
                break;
            case "unmark":
                try {
                    TaskList.markTaskAsUndone(task);
                } catch (LoopyExceptions exceptions) {
                    Ui.showMessage("Warning! " + exceptions.getMessage());
                }
                break;
            case "todo":
                try {
                    TaskList.addTodo(task); // Create Task object and add it to the list
                } catch (LoopyExceptions exceptions) {
                    Ui.showMessage("Warning! " + exceptions.getMessage());
                }
                break;
            case "deadline":
                TaskList.addDeadline(task);
                break;
            case "event":
                TaskList.addEvent(task);
                break;
            case "delete":
                TaskList.deleteTask(task);
                break;
            case "find":
                TaskList.findTask(task);
            case "bye":
                break;
            default:
                Ui.showMessage("I don't know what you're saying...?");
        }
    }
}