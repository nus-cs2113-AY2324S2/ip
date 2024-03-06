import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Represents a storage utility for loading and saving tasks from/to a file.
 */
public class Storage {

    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public List<Task> loadTasks() throws DulException {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filepath);
            if (!file.exists()) {
                throw new DulException("No existing data file found.");
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] taskDetails = line.split(" \\| ");
                if (taskDetails.length < 3) {
                    throw new DulException("Invalid task format found in data file: " + line);
                }
                switch (taskDetails[0]) {
                    case "T":
                        TodoTask todoTask = new TodoTask(taskDetails[2]);
                        if (Integer.parseInt(taskDetails[1]) == 1) {
                            todoTask.markDone();
                        }
                        tasks.add(todoTask);
                        break;
                    case "D":
                        DeadlineTask deadlineTask = new DeadlineTask(taskDetails[2], LocalDateTime.parse(taskDetails[3]));
                        if (Integer.parseInt(taskDetails[1]) == 1) {
                            deadlineTask.markDone();
                        }
                        tasks.add(deadlineTask);
                        break;
                    case "E":
                        EventTask eventTask = new EventTask(taskDetails[2], LocalDateTime.parse(taskDetails[3]), LocalDateTime.parse(taskDetails[4]));
                        if (Integer.parseInt(taskDetails[1]) == 1) {
                            eventTask.markDone();
                        }
                        tasks.add(eventTask);
                        break;
                    default:
                        throw new DulException("Invalid task format found in data file: " + line);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new DulException("Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }

    public void saveTasks(List<Task> tasks) throws DulException {
        try {
            File directory = new File(filepath).getParentFile();
            if (!directory.exists()) {
                directory.mkdirs();
            }

            FileWriter writer = new FileWriter(filepath);
            for (Task task : tasks) {
                if (task instanceof TodoTask) {
                    writer.write("T | " + (task.isDone ? 1 : 0) + " | " + task.description + "\n");
                } else if (task instanceof DeadlineTask) {
                    DeadlineTask deadlineTask = (DeadlineTask) task;
                    writer.write("D | " + (task.isDone ? 1 : 0) + " | " + task.description + " | " + deadlineTask.by.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "\n");
                } else if (task instanceof EventTask) {
                    EventTask eventTask = (EventTask) task;
                    writer.write("E | " + (task.isDone ? 1 : 0) + " | " + task.description + " | " + eventTask.from.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + " | " + eventTask.to.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new DulException("Error saving tasks to file: " + e.getMessage());
        }
    }
}

