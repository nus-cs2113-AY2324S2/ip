package natsu.util;

import natsu.task.Task;

import java.util.Scanner;
import java.util.ArrayList;

import static natsu.command.CommandExecutor.executeCommand;

public class TaskManager {

    public static final ArrayList<Task> list = new ArrayList<>();

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
