package logic;

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
                try {
                    taskManager.markTask(taskIndex, false);
                } catch (Exception e) {
                    printError(e);
                }
            } else if (command.equals("mark")) {
                int taskIndex = Character.getNumericValue(input.charAt(MARK_LENGTH)) - 1;
                try {
                    taskManager.markTask(taskIndex, true);
                } catch (Exception e) {
                    printError(e);
                }
            } else {
                switch (input) {
                case "bye":
                    return;
                case "list":
                    taskManager.listTasks();
                    break;
                default:
                    try {
                        taskManager.addTask(input);
                    } catch (Exception e) {
                        printError(e);
                    }
                    //Fallthrough
                }
            }
        }
    }

    public void printError(Exception e) {
        System.out.println(e.getMessage());
    }
}
