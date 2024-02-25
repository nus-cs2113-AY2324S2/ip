package Blue;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class StorageHandler {
    public static final String DATA_DIR_PATH = "data";
    public static final String TASK_FILE_PATH = DATA_DIR_PATH + "/tasks.txt";

    public void restoreTasks() {
        File taskFile = new File(TASK_FILE_PATH);
        try {
            Scanner s = new Scanner(taskFile);
            TaskManager taskManager = new TaskManager();
            while (s.hasNext()) {
                String[] savedTaskDetails = s.nextLine().split("\\|");
                Task savedTask = restoreTask(savedTaskDetails);
                taskManager.addTask(savedTask, false);
            }
        } catch (FileNotFoundException e) {
            // No saved tasks found, start from scratch
        }
    }

    private Task restoreTask(String[] savedDetails) {
        Task restoredTask;
        switch (savedDetails[0]) {
        case "T":
            restoredTask = new Task(savedDetails[2]);
            break;
        case "D":
            restoredTask = new Deadline(savedDetails[2], savedDetails[3]);
            break;
        case "E":
            restoredTask = new Event(savedDetails[2], savedDetails[4], savedDetails[3]);
            break;
        default:
            restoredTask = new Task();
        }
        if (savedDetails[1].equals("1")) {
            restoredTask.setDone();
        }
        return restoredTask;
    }

    public boolean hasSavedTasks(ArrayList<Task> tasks) {
        new File(DATA_DIR_PATH).mkdirs();
        File taskFile = new File(TASK_FILE_PATH);
        try {
            taskFile.createNewFile();
        } catch (IOException e) {
            return false;
        }
        boolean isAppend = false;
        for (Task task : tasks) {
            try {
                writeTaskToFile(task, isAppend);
            } catch (IOException e) {
                return false;
            }
            isAppend = true;
        }
        return true;
    }

    private void writeTaskToFile(Task task, boolean isAppend) throws IOException {
        FileWriter fw = new FileWriter(TASK_FILE_PATH, isAppend);
        fw.write(task.toSaveTextFormat());
        fw.close();
    }
}
