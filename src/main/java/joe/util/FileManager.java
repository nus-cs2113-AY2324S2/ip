package joe.util;

import java.io.*;

import joe.JoeException;
import joe.task.TaskManager;
import joe.task.Task;

public class FileManager {
    protected static final String SAVE_FILE_PATH = "./data/tasklist.txt";
    public static void loadData(TaskManager taskManager) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(SAVE_FILE_PATH));
        String taskData;
        while ((taskData = in.readLine()) != null) {
            try {
                Task newTask = InputParser.readTaskData(taskData);
                taskManager.addTask(newTask);
            } catch (JoeException ignored) {

            }
        }
    }

    public static void saveData() throws JoeException {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(SAVE_FILE_PATH));
        } catch (IOException e) {
            throw new JoeException();
        }
    }
}
