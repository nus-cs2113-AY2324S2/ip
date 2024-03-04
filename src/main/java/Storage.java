import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static final String FILE_PATH = "data/Wongster.txt";

    public static void loadTasks(ArrayList<Task> userList) throws IOException {

        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdir();
        }

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            file.createNewFile();
        }

        Scanner s = new Scanner(file);

    }

    public static void saveTasks(ArrayList<Task> userList, int taskCount) throws IOException {

        FileWriter fileWriter = new FileWriter(FILE_PATH);
        for (int i = 0; i < taskCount; i++) {
            fileWriter.write(userList.get(i).toString() + System.lineSeparator());

        }
        fileWriter.close();
    }
}