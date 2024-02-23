import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;


public class FileManager {
    private final String fileName = "C:\\Users\\koala\\OneDrive - National University of Singapore\\" +
                                    "Desktop\\NUS\\Y2S2\\CS2113\\Individual Project./data/tasks.txt";


    public void saveTasksToFile(TaskList taskList) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                fileWriter.write(task.getType() + " | " + taskStatus(task) + " | " + task.description);
                if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    fileWriter.write(" /by " + deadline.by);
                } else if (task instanceof Events) {
                    Events events = (Events) task;
                    fileWriter.write(" /from " + events.From + " /to " + events.To);
                }
                fileWriter.write("\n");
            }
            System.out.println("Tasks saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private static String taskStatus(Task task) {
        return task.isDone() ? "1" : "0";
    }

    public void loadTasksFromFile(TaskList taskList) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");

                if (parts.length == 3) { // Ensure valid format
                    String type = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];
                    Task task = null;

                    switch (type) {
                    case "T":
                        task = new Task(description);
                        break;
                    case "D":
                        String[] deadlineParts = description.split(" /by ", 2);
                        if (deadlineParts.length == 2) {
                            task = new Deadline(deadlineParts[0], deadlineParts[1]);
                        } else {
                            System.out.println("Invalid deadline format in file: " + line);
                        }
                        break;
                    case "E":
                        String[] eventParts = description.split(" /from ", 2);
                        if (eventParts.length == 2) {
                            String[] endDateParts = eventParts[1].split(" /to ", 2);
                            if (endDateParts.length == 2) {
                                task = new Events(eventParts[0], endDateParts[0], endDateParts[1]);
                            } else {
                                System.out.println("Invalid event format in file: " + line);
                            }
                        } else {
                            System.out.println("Invalid event format in file: " + line);
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid task type: " + type);
                    }

                    if (task != null) {
                        if (isDone) {
                            task.markTask();
                        }
                        taskList.addTask(task);
                    }
                } else {
                    System.out.println("Invalid line format in file: " + line);
                }
            }
            System.out.println("Tasks loaded successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

}
