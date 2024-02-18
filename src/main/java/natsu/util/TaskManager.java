package natsu.util;

import natsu.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

import static natsu.command.CommandExecutor.executeCommand;
import static natsu.util.TaskSaver.readFile;

public class TaskManager {

    public static final ArrayList<Task> list = new ArrayList<>();

    public TaskManager() {
        try (Scanner input = new Scanner(System.in)) {
            boolean isActive = true;
            readFile();
            while (isActive) {
                String userInput = input.nextLine();
                isActive = executeCommand(userInput);
            }
        }
    }
}
