import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static File file;
    public static final ArrayList<Task> tasksList = new ArrayList<>();


    public Storage(String FILE_PATH) throws IOException {
        file = new File(FILE_PATH);

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public void loadTasks() {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");

                String taskType = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String taskDescription = parts[2];

                Task task = null;
                switch (taskType) {
                    case "T":
                        task = new Todo("todo" + taskDescription);
                        break;
                    case "D":
                        task = new Deadline("deadline" + taskDescription);
                        break;
                    case "E":
                        task = new Event("event" + taskDescription);
                        break;
                    default:
                        System.out.println("Unknown task type: " + taskType);
                        continue;
                }
                if (isDone) {
                    task.markAsDone();
                }
                tasksList.add(task);
            }
            scanner.close();
        }  catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No file loaded, creating new load file");
        } catch (FileNotFoundException e) {
            System.out.println("Data file does not exist. Starting with an empty task list.");
        }
    }
}