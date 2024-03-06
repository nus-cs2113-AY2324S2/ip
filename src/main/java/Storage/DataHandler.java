package Storage;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import javax.swing.table.TableRowSorter;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage.DataHandler class provides methods for reading and writing task data to a file.
 */
public class DataHandler {
    public static final String FILE_PATH = "src/main/java/data/data.txt";

    /**
     * Appends the specified text to the file at the given path.
     *
     * @param filePath  the path of the file to write to
     * @param textToAdd the text to add to the file
     * @throws IOException if an I/O error occurs while writing to the file
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Writes the given list of tasks to the file.
     *
     * @param taskList the list of task objects to write to the file
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public static void tasksToFile(ArrayList<Task> taskList) throws IOException {
        File f = new File(FILE_PATH);
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task task : taskList) {
            if (task == null) break;
            String stringToWrite = task.getBadge() + "," + (task.getStatusIcon().equals("X") ? 1 : 0)
                    + "," + task.description + "\n";
            writeToFile(FILE_PATH, stringToWrite);
        }
    }

    /**
     * Reads the contents of the specified file and returns a list of task objects.
     *
     * @param filePath the path of the file to read
     * @return an ArrayList of Task objects read from the file
     * @throws IOException if an I/O error occurs while reading the file
     */
    public static ArrayList<Task> readFileContents(String filePath) throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        }
        Scanner s = new Scanner(f);
        ArrayList<Task> taskList = new ArrayList<>();
        for (int i = 0; s.hasNext(); i++) {
            taskList.add(processData(s.nextLine()));
        }
        return taskList;
    }

    /**
     * Processes a string containing task information and returns the corresponding Task object.
     *
     * @param s the string containing task information
     * @return a Task object representing the processed task information
     */
    private static Task processData(String s) {
        String[] taskInfo = s.split(",");
        String badge = taskInfo[0];
        switch (badge) {
        case "T":
            Todo todoObj = new Todo(taskInfo[2]);
            if (taskInfo[1].equals("1")) todoObj.setDone(true);
            return todoObj;
        case "D":
            Deadline deadlineObj = new Deadline(taskInfo[2]);
            if (taskInfo[1].equals("1")) deadlineObj.setDone(true);
            return deadlineObj;
        case "E":
            Event eventObj = new Event(taskInfo[2]);
            if (taskInfo[1].equals("1")) eventObj.setDone(true);
            return eventObj;
        }
        return null;
    }
}

