import java.util.Scanner;
public class UserInterface {
    public static final int INDEX_MARK_DONE = 5;
    public static final int INDEX_MARK_NOT_DONE = 7;
    private Scanner reader;
    private TaskManager taskManager;

    public UserInterface() {
        this.reader = new Scanner(System.in);
        this.taskManager = new TaskManager();
    }

    public void processInput() {
        String input = reader.nextLine();
        if (input.contains("unmark")) {
            int taskIndex = Character.getNumericValue(input.charAt(INDEX_MARK_NOT_DONE)) - 1;
            taskManager.markTask(taskIndex, false);
        } else if (input.contains("mark")) {
            int taskIndex = Character.getNumericValue(input.charAt(INDEX_MARK_DONE)) - 1;
            taskManager.markTask(taskIndex, true);
        } else {
            switch (input) {
            case "bye":
                return;
            case "list":
                taskManager.listTasks();
                break;
            default:
                taskManager.addTask(input);
                //Fallthrough
            }
        }
        processInput();
    }
}
