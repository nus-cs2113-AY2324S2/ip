import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {

    public static final String FILE_PATH = "data/baymax.txt";

    public static void loadTasks() throws IOException {

        File folder = new File("data");
        // Folder "data" does not exist
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File file = new File(FILE_PATH);
        // File does not exist
        if (!file.exists()) {
            file.createNewFile();
        }

        Scanner s = new Scanner(file);

        while (s.hasNext()) {
            String line = s.nextLine();
            Task task = getTask(line);
        }

    }

    // Obtains Task from text
    public static Task getTask(String line) {
        Task task;
        String[] details = line.split(" \\| ");
        String taskType = details[0];
        String taskDescription = details[2];

        switch (taskType) {

            case "T":
                task = new ToDo(taskDescription);
                break;
            case "D":
                task = new Deadline(taskDescription);
                break;
            case "E'":
                task = new Event(taskDescription);
                break;
            // Not a Task type (NEED TO FIX)
            default:
                task = new ToDo("error");
        }
        if (details[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }

}
