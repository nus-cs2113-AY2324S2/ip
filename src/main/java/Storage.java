import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Storage {

    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() {
        List<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseLineToTask(line);
                if (task != null) {
                    loadedTasks.add(task);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Will create a new file: " + filePath);
        }

        return loadedTasks;
    }

    private Task parseLineToTask(String line) {
        String[] parts = line.split("\\|", -1);
        if (parts.length < 3) {
            System.out.println("Invalid task format: " + line);
            return null;
        }

        String taskType = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        switch (taskType) {
            case "T":
                TodoTask todoTask = new TodoTask(description);
                if (isDone) {
                    todoTask.markAsDone();
                }
                return todoTask;

            case "D":
                String deadlineStr = parts[3].trim();
                try {
                    LocalDateTime deadline = LocalDateTime.parse(deadlineStr, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
                    return new DeadlineTask(description, deadline);
                } catch (DateTimeParseException e) {
                    System.err.println("Error parsing deadline datetime for task: " + description + ". Please ensure it's in the correct format 'DD-MM-YYYY HHmm'.");
                    return null;
                }

            case "E":

                String startTimeStr = parts[3].trim();
                String endTimeStr = parts[4].trim();

                LocalDateTime startTime, endTime;

                try {
                    startTime = LocalDateTime.parse(startTimeStr, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
                    endTime = LocalDateTime.parse(endTimeStr, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));

                } catch (DateTimeParseException e) {
                    System.err.println("Invalid event time format. Please use the format 'DD-MM-YYYY HHmm'.");
                    return null;
                }

                return new EventTask(description, startTime, endTime);

            default:
                System.out.println("Unknown task type in file: " + taskType);
                break;
        }
        return null;
    }


    public void save(List<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filePath, false);

            for (Task task : tasks) {
                writer.write(task.toString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to file.");
        }
    }

}