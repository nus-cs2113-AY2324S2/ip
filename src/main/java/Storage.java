import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class handles the loading and saving of tasks from/to a file.
 */
public class Storage {
    private static File file;

    /**
     * Constructs a Storage object with the specified file path.
     * @param FILE_PATH File path to store tasks.
     * @throws IOException If an I/O error occurs while creating the file.
     */
    public Storage(String FILE_PATH) throws IOException {
        file = new File(FILE_PATH);

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Loads tasks from the file and adds them to the specified TaskList.
     * @param tasks TaskList to which tasks will be added.
     */
    public void loadTasks(TaskList tasks) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\s*\\|\\s*", -1);

                String taskType = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String taskDescription = parts[2].trim();

                switch (taskType) {
                    case "T":
                        tasks.add(new Todo(isDone, taskDescription));
                        break;
                    case "D":
                        String deadlineBy = parts[3].trim();
                        tasks.add(new Deadline(isDone, taskDescription, deadlineBy));
                        break;
                    case "E":
                        String eventFrom = parts[3].trim();
                        String eventTo = parts[4].trim();
                        tasks.add(new Event(isDone, taskDescription, eventFrom, eventTo));
                        break;
                    default:
                        System.out.println("Unknown task type: " + taskType);
                        break;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Data file does not exist. Starting with an empty task list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid data format in file.");
        }
    }

    /**
     * Saves tasks to the file.
     */
    public void saveTasks() {
        File file = new File("./data/Bart.txt");

        try (FileWriter writer = new FileWriter(file)) {
            for (Task task: Task.getAllTasks()) {
                if (task != null) {
                    String description = task.description;
                    String mark = task.isDone ? "1" : "0";
                    String taskType = task.getTaskType().replace("]", "").replace("[", "").trim();
                    writer.write(taskType + " | " + mark + " | " + description);
                    if (Deadline.class.isInstance(task)) {
                        Deadline deadlineTask = (Deadline) task;
                        writer.write(" | " + deadlineTask.getBy());
                    } else if (Event.class.isInstance(task)) {
                        Event eventTask = (Event) task;
                        writer.write(" | " + eventTask.getFrom() + " | " + eventTask.getTo());
                    }
                    writer.write(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println("Saving error: " + e.getMessage());
        }
    }
}