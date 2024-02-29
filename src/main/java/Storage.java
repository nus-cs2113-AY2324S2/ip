import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static File file;

    public Storage(String FILE_PATH) throws IOException {
        file = new File(FILE_PATH);

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

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
}