package geepee.system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import geepee.task.Task;
import geepee.task.Todo;
import geepee.task.Deadline;
import geepee.task.Event;

public class FileHandler {

    /** Index of task type (todo, deadline, event) in a String array */
    private static final int TASK_TYPE_INDEX = 0;
    /** Index of task completion status in a String array */
    private static final int TASK_STATUS_INDEX = 1;
    /** Index of task description in a String array */
    private static final int TASK_DESCRIPTION_INDEX = 2;

    /** Index of deadline in a String array */
    private static final int DEADLINE_BY_INDEX = 3;

    /** Index of start of event in a String array */
    private static final int EVENT_FROM_INDEX = 3;
    /** Index of end of event in a String array */
    private static final int EVENT_TO_INDEX = 4;

    /** Filepath to read/write data */
    private String filePath;

    /**
     * Initialises an instance of the FileHandler class.
     * 
     * @param filePath Filepath of data file to read from and write to.
     */
    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes a line of text into the data file.
     * 
     * @param textToAdd Text to be added.
     * @throws IOException If file cannot be found.
     */
    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Retrieves tasks data stored in the data file.
     * 
     * @return Array of tasks retrieved from the data file.
     * @throws FileNotFoundException If file cannot be found.
     */
    public ArrayList<Task> getTasksFromFile() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        String line = "";
        while (s.hasNext()) {
            line = s.nextLine();
            String[] words = line.split(";");
            String taskType = words[TASK_TYPE_INDEX];
            boolean taskStatus = words[TASK_STATUS_INDEX].equals("X") ? true : false;
            if (taskType.equals("T")) {
                tasks.add(new Todo(words[TASK_DESCRIPTION_INDEX], taskStatus));
            } else if (taskType.equals("D")) {
                tasks.add(new Deadline(words[TASK_DESCRIPTION_INDEX], words[DEADLINE_BY_INDEX], taskStatus));
            } else if (taskType.equals("E")) {
                tasks.add(new Event(words[TASK_DESCRIPTION_INDEX], words[EVENT_FROM_INDEX], words[EVENT_TO_INDEX],
                        taskStatus));
            }
        }
        return tasks;
    }

    /**
     * Writes a given array of tasks into the data file.
     * 
     * @param tasks Array of tasks to write into data file.
     */
    public void writeTasks(ArrayList<Task> tasks) {
        try {
            String newData = "";
            for (Task task : tasks) {
                newData += task.toFileFriendlyString() + System.lineSeparator();
            }
            writeToFile(newData);
        } catch (IOException e) {
            SystemMessage.printIOExceptionMessage();
        }
    }
}
