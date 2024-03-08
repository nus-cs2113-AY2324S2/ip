package logic;

import java.util.Scanner;

/**
 * Class used to interact with the user
 */
public class UserInterface {
    public static final int MARK_LENGTH = 5;
    public static final int UNMARK_LENGTH = 7;
    public static final int DELETE_LENGTH = 7;
    private Scanner reader;
    private Storage storage;
    private TaskManager taskManager;

    /**
     * Constructor for UserInterface. Instantiates a Scanner with which to read user input
     *
     * @param storage The Storage object containing the data file to save to
     * @param taskManager The TaskManager object within which tasks will be managed
     */
    public UserInterface(Storage storage, TaskManager taskManager) {
        this.reader = new Scanner(System.in);
        this.storage = storage;
        this.taskManager = taskManager;
    }

    /**
     * Reads user input and determines the command the user is giving
     */
    public void processInput() {
        while (true) {
            String input = reader.nextLine();
            String command = input.split(" ")[0];
            if (command.equals("unmark")) {
                int taskIndex = Parser.processIndex(input);
                try {
                    taskManager.markTask(taskIndex, false);
                    storage.saveDataToTextFile();
                } catch (Exception e) {
                    printError(e);
                }
            } else if (command.equals("mark")) {
                int taskIndex = Parser.processIndex(input);
                try {
                    taskManager.markTask(taskIndex, true);
                    storage.saveDataToTextFile();
                } catch (Exception e) {
                    printError(e);
                }
            } else if (command.equals("delete")) {
                int deleteIndex = Parser.processIndex(input);
                try {
                    taskManager.deleteTask(deleteIndex);
                    storage.saveDataToTextFile();
                } catch (Exception e) {
                    printError(e);
                }
            } else if (command.equals("find")) {
                taskManager.findTask(input.split(" ")[1]);
            } else if (command.equals("bye")) {
                return;
            } else if (command.equals("list")) {
                taskManager.listTasks();
            } else {
                try {
                    taskManager.addTask(input);
                    storage.saveDataToTextFile();
                } catch (Exception e) {
                    printError(e);
                }
            }
        }
    }

    /**
     * Prints exception message when an exception is caught
     *
     * @param e The exception caught whose message is to be printed
     */
    public static void printError(Exception e) {
        System.out.println(e.getMessage());
    }
}
