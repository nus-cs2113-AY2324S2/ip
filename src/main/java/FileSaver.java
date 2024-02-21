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
            FileWriter fw = new FileWriter(file);
            String type = "T";
            String toDoText = type + SEPARATOR + 0 + SEPARATOR + todo.description;
            fw.write(toDoText);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something happened: " + e.getMessage());
        }
    }

    public static void saveDeadline(Task[] tasks) {
        try {
            FileWriter fw = new FileWriter(file);
            String deadlineText = "";
            fw.write(deadlineText);
        } catch (IOException e) {
            System.out.println("Something happened: " + e.getMessage());
        }
    }

    public static void saveEvent(Task[] tasks) {
        try {
            FileWriter fw = new FileWriter(file);
            String eventText = "";
            fw.write(eventText);
        } catch (IOException e) {
            System.out.println("Something happened: " + e.getMessage());
        }
    }

    public static void saveMark(Task[] tasks) {

    }
}
