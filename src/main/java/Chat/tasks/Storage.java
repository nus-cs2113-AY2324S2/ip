package Chat.tasks;

import Chat.tasks.TaskList;
import Chat.tasks.Task;
import Chat.exceptions.RepeatMark;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Storage {
    private static final String FILE_PATH = "tasks.txt";

    public Storage() {
        // Constructor if needed for any initialization
    }

    /**
     * Load tasks stored in the txt file with a specified file path.
     * If the file exist, reads every line and add into the taskList.
     * @return A taskList containing all the tasks from the file.
     * @throws FileNotFoundException If file not found
     */
    public TaskList loadTasks() throws FileNotFoundException {
        TaskList taskList = new TaskList();
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    String taskString = scanner.nextLine();
                    Task task = Task.fromString(taskString);
                    taskList.addTask(task);
                }
                scanner.close();
            } catch (IOException | RepeatMark e) {
                System.out.println("Error loading tasks: " + e.getMessage());
            }
        }
        return taskList;
    }

    /**
     * Save tasks added by the user to a file with specific relative path.
     * @param taskList TaskList saved to the file.
     */
    public void saveTasks(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : taskList.getAllTasks()) {
                writer.write(task.shortType + " | " + task.numisDone() +
                        " | " + task.getDescription() + " | " + task.time + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
