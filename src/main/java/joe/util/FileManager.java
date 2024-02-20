package joe.util;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Scanner;

import joe.JoeException;
import joe.task.TaskManager;
import joe.task.Task;

public class FileManager {
    protected static final String DATA_PATH = "./data/";
    protected static final String FILE_NAME = "tasklist.txt";
    protected static final String SAVE_FILE_PATH = DATA_PATH + FILE_NAME;

    public static void loadData(TaskManager taskManager) throws FileNotFoundException {
        File directory = new File(DATA_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
            return;
        }

        File saveFile = new File(SAVE_FILE_PATH);
        if (!saveFile.exists()) {
            return;
        }

        Scanner s = new Scanner(saveFile);
        String taskData;
        int corruptedData = 0;
        while (s.hasNext()) {
            try {
                taskData = s.nextLine();
                Task newTask = FileParser.readTaskData(taskData);
                taskManager.addTask(newTask);
            } catch (JoeException e) {
                corruptedData++;
            }
        }
        if (corruptedData != 0) {
            Printer.printCorruptedFileError(corruptedData);
        }
        s.close();
    }

    public static void saveData(ArrayList<Task> tasks) throws IOException {
        File saveFile = new File(SAVE_FILE_PATH);
        if (!saveFile.createNewFile()) {
            saveFile.delete();
            saveFile.createNewFile();
        }

        FileWriter fw = new FileWriter(saveFile);
        BufferedWriter writer = new BufferedWriter(fw);
        for (Task t : tasks) {
            String data = null;
            switch (t.getTaskType()) {
            case TODO:
                data = FileParser.getTodoData(t.getTaskStatus());
                break;
            case DEADLINE:
                data = FileParser.getDeadlineData(t.getTaskStatus());
                break;
            case EVENT:
                data = FileParser.getEventData(t.getTaskStatus());
                break;
            }

            if (data != null) {
                writer.write(data + "\n");
            }
        }

        writer.close();
        fw.close();
    }
}
