package natsu.util;

import natsu.task.Task;

import java.util.Scanner;

import static natsu.command.CommandExecutor.executeCommand;

public class TaskManager {
    private static final int MAX_TASKS = 100;
    public static int taskCount = 0;

    public static final Task[] list = new Task[MAX_TASKS];

    public TaskManager() {
        try (Scanner input = new Scanner(System.in)) {
            boolean isActive = true;
            while (isActive) {
                String userInput = input.nextLine();
                isActive = executeCommand(userInput);
            }
        }
    }
}
