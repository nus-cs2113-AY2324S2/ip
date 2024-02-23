//import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileWriter;
//import java.util.Scanner;
//import java.util.ArrayList;
import java.io.*;

public class FileManager {
    private final String fileName = "C:\\Users\\koala\\OneDrive - National University of Singapore\\" +
                                    "Desktop\\NUS\\Y2S2\\CS2113\\Individual Project./data/tasks.txt";

    public void saveTasksToFile(TaskList taskList) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                fileWriter.write(task.getType() + " | " + (task.isDone() ? 1 : 0) + " | " + task + "\n");
                //fileWriter.close();
            }
            System.out.println("Tasks saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }


}
