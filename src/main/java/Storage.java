import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {

    private static String textFilePath = "data/listOfTasks.txt";
    private static String directoryPath = "data";

    public static ArrayList<Task> loadListOfTasks() {
        ArrayList<Task> listOfTasks = new ArrayList<>();

        File textFile = new File(textFilePath);
        File directory = new File(directoryPath);

        try {
            if (!directory.exists()) {
                directory.mkdirs();
            }

            if (!textFile.exists()) {
                textFile.createNewFile();
            }

            Scanner s = new Scanner(textFile);
            while (s.hasNext()) {
                String line = s.nextLine();
                Task task = CommandManager.parseTaskFromTextFileLine(line);

                listOfTasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Unable to load from Storage: " + e.getMessage());
        }

        return listOfTasks;
    }

    // Unused Function
    public static void writeTaskToStorage(Task task) {
        try {
            FileWriter fw = new FileWriter(textFilePath, true);
            fw.write(task.toFileFormat() + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to save Task Data: " + e.getMessage());
        }
    }

    public static void writeAllTasksToStorage(ArrayList<Task> listOfTasks) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(textFilePath));

            for (Task task : listOfTasks) {
                bw.write(task.toFileFormat());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Unable to save Task Data: " + e.getMessage());
        }
    }
}
