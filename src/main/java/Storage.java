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
            return "D | " + (task.isDone() ? "1" : "0") + " | " + deadlineTask.getDescription();
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            return "E | " + (task.isDone() ? "1" : "0") + " | " + eventTask.getDescription();
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
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskType) {
        case "T":
            Task newTodo = new Todo(description);
            newTodo.setDone(isDone);
            return newTodo;
        case "D":
            String by = parts[3];
            Deadline newDeadline = new Deadline(description, by);
            newDeadline.setDone(isDone);
            return newDeadline;
        case "E":
            String dateTimeString = parts[3];
            String[] dateTimeParts = dateTimeString.split(" to ");
            String from = dateTimeParts[0];
            String to = dateTimeParts[1];

            Event newEvent = new Event(description, from, to);
            newEvent.setDone(isDone);
            return newEvent;
        default:
            return null;
        }
    }
}
