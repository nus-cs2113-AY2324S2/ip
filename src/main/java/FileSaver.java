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
        try {
            FileWriter fw = new FileWriter(file, true);
            String type = "T";
            int notDone = 0;
            String toDoText = type + SEPARATOR + notDone
                    + SEPARATOR + todo.description + System.lineSeparator();
            fw.write(toDoText);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something happened: " + e.getMessage());
        }
    }

    public static void saveDeadline(Deadline deadline) {
        try {
            FileWriter fw = new FileWriter(file, true);
            String type = "D";
            int notDone = 0;
            String description = deadline.description;
            String by = deadline.by;
            String deadlineText = type + SEPARATOR + notDone
                    + SEPARATOR + description + SEPARATOR + by  + System.lineSeparator();
            fw.write(deadlineText);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something happened: " + e.getMessage());
        }
    }

    public static void saveEvent(Event event) {
        try {
            FileWriter fw = new FileWriter(file, true);
            String type = "E";
            int notDone = 0;
            String description = event.description;
            String from = event.from;
            String to = event.to;
            String eventText = type + SEPARATOR + notDone + SEPARATOR + description
                    + SEPARATOR + from + " to " + to + System.lineSeparator();
            fw.write(eventText);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something happened: " + e.getMessage());
        }
    }

    public static void saveMark(Task[] tasks) {

    }
}
