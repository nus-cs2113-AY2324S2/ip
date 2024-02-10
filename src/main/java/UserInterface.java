import java.util.Scanner;
public class UserInterface {
    public static final int MARK_LENGTH = 5;
    public static final int UNMARK_LENGTH = 7;
    private Scanner reader;
    private TaskManager taskManager;

    public UserInterface() {
        this.reader = new Scanner(System.in);
        this.taskManager = new TaskManager();
    }

    public void processInput() {
        while (true) {
            String input = reader.nextLine();
            String command = input.split(" ")[0];
            if (command.equals("unmark")) {
                int taskIndex = Character.getNumericValue(input.charAt(UNMARK_LENGTH)) - 1;
                if (taskManager.markTask(taskIndex, false).equals("error")) {
                    printError();
                }
            } else if (command.equals("mark")) {
                int taskIndex = Character.getNumericValue(input.charAt(MARK_LENGTH)) - 1;
                if (taskManager.markTask(taskIndex, true).equals("error")) {
                    printError();
                }
            } else {
                switch (input) {
                case "bye":
                    return;
                case "list":
                    taskManager.listTasks();
                    break;
                default:
                    if (taskManager.addTask(input).equals("error")) {
                        printError();
                    }
                    //Fallthrough
                }
            }
        }
    }

    public void printError() {
        System.out.println("ERROR: Invalid input");
    }
}
