import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Storage {

    /**
     * Constructs a new Storage object associated with a file path.
     *
     */
    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }


    public ArrayList<Task> load(String FILE_PATH) {

        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return null;
            }

            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                String[] parts = line.split("\\|");

                String taskType = parts[0].trim();
                boolean isDone = parts[2].trim().equals("true");
                String taskDescription = parts[1].trim();

                Task task;
                switch (taskType) {
                    case "T":
                        task = new TodoTask(taskDescription);
                        break;
                    case "D":
                        String deadline = parts[3].trim();
                        LocalDateTime formattedDeadline = convertStringToLocalDateTime(deadline);
                        task = new DeadlineTask(taskDescription, formattedDeadline);
                        break;
                    case "E":
                        String startTime = parts[3].trim();
                        String endTime = parts[4].trim();

                        LocalDateTime formattedStartTime = convertStringToLocalDateTime(startTime);
                        LocalDateTime formattedEndTime = convertStringToLocalDateTime(endTime);

                        task = new EventTask(taskDescription, formattedStartTime, formattedEndTime);
                        break;
                    default:
                        System.out.println("Unknown task type: " + taskType);
                        continue;
                }

                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
            scanner.close();

            return tasks;
        }  catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("ERROR" + e);
            return null;
        }
    }

    /*
    * Converts a date and time in string format to a LocalDateTime object.
    *
    * */
    private static LocalDateTime convertStringToLocalDateTime(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return LocalDateTime.parse(string, formatter);
    }

    /*
    * Saves tasks to file
    * Each task is converted to a string representation suitable for file storage.
    * */
    public static void saveTasksToFile(ArrayList<Task> tasks) {
        File file = new File("./data/tasks.txt");

        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (FileWriter writer = new FileWriter(file)) {
            for (Task task: tasks) {
                String data = task.toFileString() + System.lineSeparator();

                writer.write(data);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}