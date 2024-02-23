import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.File;
import java.util.List;
import java.io.IOException;
public class FileSaver {

    public static final String DIRECTORYPATH = "./data/";
    public static final String FILEPATH = DIRECTORYPATH + "MsChatty:).txt";

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(textToAppend);
        }
    }

    public static void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            File directory = new File(DIRECTORYPATH);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            FileWriter fileWriter = new FileWriter(FILEPATH, false); // Open the file for writing, false to overwrite
            fileWriter.write("");

            for (Task task : tasks) {
                appendToFile(FILEPATH, task.taskInFileFormat());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public static ArrayList<Task> revertTasksToListFormat(List<String> tasks) {
        ArrayList<Task> revertedTasks = new ArrayList<>();
        try {
            for (String task : tasks) {
                revertedTasks.add(revertTaskToListFormat(task));

            }
        } catch (IllegalArgumentException e) {
            System.out.println("Data file is corrupted: " + e.getMessage());
        }
        return revertedTasks;
    }

    public static Task revertTaskToListFormat(String line) {
        String[] arrayOfTask = line.split(" \\| ", 3);
        boolean isDone = arrayOfTask[1].equals("1");
        String description;

        switch (arrayOfTask[0]) {
        case "T":
            description = arrayOfTask[2];
            Task newTodo = new Todo(description);
            newTodo.setDone(isDone);
            return newTodo;
        case "D":
            String[] arrayOfDeadlineSubsequentParts = arrayOfTask[2].split(" \\| ");
            Deadline newDeadline = new Deadline(arrayOfDeadlineSubsequentParts[0], arrayOfDeadlineSubsequentParts[1]);
            newDeadline.setDone(isDone);
            return newDeadline;
        case "E":
            String[] arrayOfEventSubsequentParts = arrayOfTask[2].split("[|\\-]");
            Event newEvent = new Event(arrayOfEventSubsequentParts[0], arrayOfEventSubsequentParts[1], arrayOfEventSubsequentParts[2]);
            newEvent.setDone(isDone);
            return newEvent;
        default:
            return null;
        }
    }

    public static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        Path filePath = Paths.get(FILEPATH);

        try {
            if (Files.exists(filePath)) {
                List<String> lines = Files.readAllLines(filePath);
                tasks.addAll(revertTasksToListFormat(lines));
            }
        } catch (IOException e) {
            System.out.println("Error reading tasks from file: " + e.getMessage());
        }

        return tasks;
    }
}

