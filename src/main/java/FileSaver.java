import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileSaver {
    protected static final String DATA_PATH = "./data/tonytask.txt";
    protected static final String SEPARATOR = " | ";
    protected static File file = new File(DATA_PATH);

    public static void checkFileExists() throws IOException {
        File directory = new File("./data");
        if (!directory.exists()) {
           directory.mkdirs();
        }

        if (!file.exists()) {
            System.out.println("File not found. File will be created!");
            file.createNewFile();
        }
    }

    public static void saveTodo(Todo todo) {
        String type = "T";
        String description = todo.description;
        int notDone = todo.getStatusIcon().equals(" ") ? 0 : 1;
        String toDoText = type + SEPARATOR + notDone
                + SEPARATOR + description + System.lineSeparator();
        saveData(toDoText);
    }

    public static void saveDeadline(Deadline deadline) {
        String type = "D";
        int notDone = deadline.getStatusIcon().equals(" ") ? 0 : 1;
        String description = deadline.description;
        String by = deadline.by;
        String deadlineText = type + SEPARATOR + notDone
                + SEPARATOR + description + SEPARATOR + by  + System.lineSeparator();
        saveData(deadlineText);
    }

    public static void saveEvent(Event event) {
        String type = "E";
        int notDone = event.getStatusIcon().equals(" ") ? 0 : 1;
        String description = event.description;
        String from = event.from;
        String to = event.to;
        String eventText = type + SEPARATOR + notDone + SEPARATOR + description
                + SEPARATOR + from + " to " + to + System.lineSeparator();
        saveData(eventText);
    }

    public static void saveMark(Task[] tasks) {

    }

    public static void saveData(String taskCommand) {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(taskCommand);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something happened: " + e.getMessage());
        }
    }
}
