package Sinep;

import Storage.StorageCommand;
import TaskList.HandleTask;
import TaskList.Task;
import UI.CommandUI;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;


public class Sinep {
    public static final String FILE_PATH = "src/main/java/Sinep/Sinep.txt";
    public static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * The main method that initializes the application, loads existing tasks, and processes user input, then save tasks.
     *
     * @param args Command-line arguments(not used).
     * @throws IOException If there is an issue with loading or saving tasks to the file.
     */
    public static void main(String[] args) throws IOException {
        StorageCommand.loadTaskFile(taskList);
        Scanner scanner = new Scanner(System.in);
        CommandUI.printGreeting();
        while (true) {
            String input = scanner.nextLine();
            String command = input.split(" ", 2)[0]; // Get the first word of the input as the command
            if (HandleTask.taskCommands(command, scanner, input)) return;
            StorageCommand.saveTasks(taskList);
        }
    }
}