import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasksToFile(TaskList tasks) throws IOException {
        Path filePath = Paths.get(this.filePath);

        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }

        List<String> encodedTasks = encodeTasks(tasks.getTasks());
        Files.write(filePath, encodedTasks);
    }

    public TaskList loadTasksToFile() throws IOException, JaneDataCorruptedException {
        Path filePath = Paths.get(this.filePath);
        TaskList tasks;

        if (Files.exists(filePath)) {
            try {
                List<String> lines = Files.readAllLines(filePath);
                tasks = new TaskList(decodeTasks(lines));
            } catch (FileNotFoundException e) {
                throw new FileNotFoundException("Data file not found");
            } catch (IOException e) {
                throw new IOException("Error reading tasks from the file", e);
            } catch (JaneDataCorruptedException e) {
                throw e;
            }
        } else {
            tasks = new TaskList();
        }

        return tasks;
    }

    public static List<String> encodeTasks(List<Task> tasks) {
        List<String> encodedTasks = new ArrayList<>();
        for (Task task : tasks) {
            encodedTasks.add(encodeTask(task));
        }
        return encodedTasks;
    }

    public static String encodeTask(Task task) {
        if (task instanceof Todo) {
            return "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            return "D | " + (task.isDone() ? "1" : "0") + " | " + deadlineTask.getDescription() + " | " + deadlineTask.getBy();
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            return "E | " + (task.isDone() ? "1" : "0") + " | " + eventTask.getDescription() + " | " + eventTask.getStart() + "-" + eventTask.getEnd();
        } else {
            return "";
        }
    }

    public static ArrayList<Task> decodeTasks(List<String> lines) throws JaneDataCorruptedException {
        ArrayList<Task> decodedTasks = new ArrayList<>();
        try {
            for (String line : lines) {
                decodedTasks.add(decodeTask(line));
            }
        } catch (IllegalArgumentException e) {
            throw new JaneDataCorruptedException("Data file is corrupted: " + e.getMessage());
        }

        return decodedTasks;
    }

    public static Task decodeTask(String line) {
        String[] parts = line.split(" \\| ", 3);
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description;

        switch (taskType) {
        case "T":
            description = parts[2];
            Task newTodo = new Todo(description);
            newTodo.setDone(isDone);
            return newTodo;
        case "D":
            String[] descriptionByString = parts[2].split(" \\| ");
            description = descriptionByString[0];
            String by = descriptionByString[1];
            Deadline newDeadline = new Deadline(description, by);
            newDeadline.setDone(isDone);
            return newDeadline;
        case "E":
            String[] descriptionStartEndString = parts[2].split(" \\| ");
            description = descriptionStartEndString[0];
            String[] dateTimeParts = descriptionStartEndString[1].split("-");
            String start = dateTimeParts[0];
            String end = dateTimeParts[1];

            Event newEvent = new Event(description, start, end);
            newEvent.setDone(isDone);
            return newEvent;
        default:
            return null;
        }
    }

    public void ensureDataFileExists() throws IOException {
        Path filePath = Paths.get(this.filePath);
        // Check if the file exists or create it
        if (!Files.exists(filePath)) {
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            Files.createFile(filePath);
        }
    }
}
