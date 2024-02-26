package logic;

import java.util.Scanner;
public class UserInterface {
    public static final int MARK_LENGTH = 5;
    public static final int UNMARK_LENGTH = 7;
    public static final int DELETE_LENGTH = 7;
    private Scanner reader;
    private Storage storage;
    private TaskManager taskManager;

    public UserInterface(Storage storage, TaskManager taskManager) {
        this.reader = new Scanner(System.in);
        this.storage = storage;
        this.taskManager = taskManager;
    }

    public void processInput() {
        while (true) {
            String input = reader.nextLine();
            String command = input.split(" ")[0];
            if (command.equals("unmark")) {
                int taskIndex = Character.getNumericValue(input.charAt(UNMARK_LENGTH)) - 1;
                try {
                    taskManager.markTask(taskIndex, false);
                    storage.saveDataToTextFile();
                } catch (Exception e) {
                    printError(e);
                }
            } else if (command.equals("mark")) {
                int taskIndex = Character.getNumericValue(input.charAt(MARK_LENGTH)) - 1;
                try {
                    taskManager.markTask(taskIndex, true);
                    storage.saveDataToTextFile();
                } catch (Exception e) {
                    printError(e);
                }
            } else if (command.equals("delete")) {
                int deleteIndex = Character.getNumericValue(input.charAt(DELETE_LENGTH)) - 1;
                try {
                    taskManager.deleteTask(deleteIndex);
                    storage.saveDataToTextFile();
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
                        taskManager.addTask(input, false);
                        storage.saveDataToTextFile();
                    } catch (Exception e) {
                        printError(e);
                    }
                    //Fallthrough
                }
            }
        }
    }

    public static void printError(Exception e) {
        System.out.println(e.getMessage());
    }
}
