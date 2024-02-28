package Gene.storage;

import Gene.GeneException;
import Gene.task.Deadline;
import Gene.task.Event;
import Gene.task.Task;
import Gene.task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Represents the file storage of the Gene chatbot.
 * Responsible for saving or loading tasks information to/from the text file.
 */
public class Storage {
    private final String FILE_PATH = "./data/Gene.txt";

    /**
     * Loads the task list data from the text file and returns the task list.
     *
     * @return The task list with the loaded tasks.
     */
    public ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.out.println("Creating new file...");
                File directory = new File("./data");
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.createNewFile();
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                Task task = getTask(parts);
                taskList.add(task);
            }
            br.close();

        } catch (IOException | GeneException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
        return taskList;
    }

    /**
     * Create new tasks objects based on the task type to be added in the task list.
     *
     * @param parts The task data from the task list to be processed.
     * @return A task object based on the task type.
     * @throws GeneException if there is any unknown task type.
     */
    private Task getTask(String[] parts) throws GeneException {
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        Task task;
        switch (taskType) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                String by = parts[3];
                task = new Deadline(description, by);
                break;
            case "E":
                String from = parts[3];
                String to = parts[4];
                task = new Event(description, from, to);
                break;
            default:
                throw new GeneException("Unknown task type found in file.");
        }
        task.setDone(isDone);
        return task;
    }

    /**
     * Save tasks to the text file after changes are made to the task list.
     *
     * @param tasks The task list of all tasks.
     */
    public void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (Task task : tasks) {
                String taskType = task.taskType;
                writer.write(taskType + " | " + (task.isDone() ? "1" : "0")
                        + " | " + task.toFileString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
