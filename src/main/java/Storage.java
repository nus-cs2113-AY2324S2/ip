import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                Task task = createTaskFromData(parts);
                if (task != null) {
                    loadedTasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            // If the file doesn't exist, an empty list will be returned
        }

        return loadedTasks;
    }

    private Task createTaskFromData(String[] parts) throws DukeException {
        Task task = null;
        boolean isDone = "1".equals(parts[1]);
        String description = parts[2];

        switch (parts[0]) {
            case "T":
                task = new Task(description);
                break;
            case "D":
                if (parts.length < 4) throw new DukeException("Invalid data for deadline task.");
                task = new Deadline(description, parts[3]);
                break;
            case "E":
                if (parts.length < 5) throw new DukeException("Invalid data for event task.");
                task = new Event(description, parts[3], parts[4]);
                break;
            default:
                throw new DukeException("Unknown task type in data file.");
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    public void save(ArrayList<Task> tasks) throws DukeException {
        try {
            PrintWriter writer = new PrintWriter(filePath);

            for (Task task : tasks) {
                writer.println(taskToFileString(task));
            }

            writer.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Error saving tasks to file.");
        }
    }

    private String taskToFileString(Task task) {
        String type = "T";
        String isDone = task.isDone() ? "1" : "0";
        String description = task.getDescription();
        String additionalInfo = "";

        if (task instanceof Deadline) {
            type = "D";
            additionalInfo = " | " + ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            type = "E";
            additionalInfo = " | " + ((Event) task).getStart() + " | " + ((Event) task).getEnd();
        }

        return type + " | " + isDone + " | " + description + additionalInfo;
    }
}
