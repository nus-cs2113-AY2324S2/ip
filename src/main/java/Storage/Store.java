package Storage;

import Events.*;
import Exceptions.HikoExceptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Store {

    private String filePath;
    private TaskList taskList;


    /**
     * Instance of the function
     *
     * @param filePath Takes in the filepath
     */

    public Store(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Sets the TaskList object for this store and loads tasks from the file.
     *
     * @param taskList The TaskList instance to which loaded tasks will be added.
     */

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
        loadTasks();
    }

    /**
     * Loads tasks from the file specified by filePath. If the file does not exist,
     * it creates the file and directories as needed. Loaded tasks are added to the
     * TaskList associated with this Store.
     */
    private void loadTasks() {
        try {
            List<Task> tasks = readTasks();
            tasks.forEach(task -> taskList.addTask(task));
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks: " + e.getMessage());
        }
    }

    /**
     * Reads tasks from the file and returns them as a List.
     * Tasks are parsed based on a predefined format.
     *
     * @return A List of Task objects read from the file.
     * @throws IOException If an IO error occurs reading from the file.
     */
    private List<Task> readTasks() throws IOException {
        Path path = Paths.get(this.filePath);
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }

        List<String> lines = Files.readAllLines(path);
        List<Task> tasks = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(" \\| ");
            try {
                Task task = null;
                switch (parts[0]) {
                case "T":
                    task = new ToDo(parts[2]);
                    break;
                case "D":
                    task = new Deadline(parts[2] + " /by " + parts[3]);
                    break;
                case "E":
                    task = new Event(parts[2] + " /from " + parts[3] + " /to " + parts[4]);
                    break;
                }
                if (task != null) {
                    if (parts[1].equals("1")) task.markAsDone();
                    tasks.add(task);
                }
            } catch (Exception e) {
                System.out.println("An error occurred while parsing a task: " + e.getMessage());
            }
        }
        return tasks;
    }

    /**
     * Saves all tasks from the TaskList to the file in a specified format.
     * If saving fails, an error message is printed to the console.
     */
    public void saveTasks() {
        List<String> lines = taskList.getTasks().stream()
                .map(Task::toFileFormat)
                .collect(Collectors.toList());
        try {
            Files.write(Paths.get(this.filePath), lines);
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }
}
