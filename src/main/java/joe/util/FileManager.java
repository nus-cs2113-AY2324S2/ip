package joe.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import joe.JoeException;
import joe.task.TaskManager;
import joe.task.Task;

public class FileManager {
    protected static final String SAVE_FILE_PATH = "./data/tasklist.txt";
    public static void loadData(TaskManager taskManager) throws FileNotFoundException {
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
    }

    public static void saveData(ArrayList<Task> tasks) throws IOException {
        File saveFile = new File(SAVE_FILE_PATH);
        if (!saveFile.createNewFile()) {
            saveFile.delete();
            saveFile.createNewFile();
        }

        FileWriter fw = new FileWriter(saveFile);
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
                fw.write(data + "\n");
            }
        }

        fw.close();
    }
}
