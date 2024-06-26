package roleypoley.data;

import roleypoley.task.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Store the data into textfile
 */
public class WriteFile {

    /**
     * Converts data in ArrayList and store into a textfile
     *
     * @param filePath path of the textfile
     * @param taskList ArrayList that contains data to be stored
     * @throws IOException if there is an error in converting and storing data
     */
    public static void writeToFile(String filePath, ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        taskList.forEach(task -> {
            int markDoneOrUndone = 0;
            if (task.getStatusIcon().equals("X")) {
                markDoneOrUndone = 1;
            }
            try {
                fw.write(task.getTaskTypeIcon() + " | " + markDoneOrUndone + " | " + task.getDescription() + "\n");
            } catch (IOException ex) {
                System.out.println("File Error: " + ex.getMessage());
                throw new RuntimeException(ex);
            }
        });
        fw.close();
    }
}
