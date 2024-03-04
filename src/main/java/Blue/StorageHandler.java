package Blue;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * The part of Blue that handles disk write and access.
 */
public class StorageHandler {
    private static final String DATA_DIR_PATH = "data";
    private static final String TASK_FILE_PATH = DATA_DIR_PATH + "/tasks.txt";
    private TaskManager taskManager;

    /**
     * Public constructor for storage handler.
     * Note that it need only be called once as it suffices to have one storage handler per program.
     *
     * @param taskManager The task manager of Blue.
     */
    public StorageHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Restores the state of the application from a previous run.
     * Simply restores tasks from disk for now.
     */
    public void restoreState() {
        restoreTasks();
    }

    /**
     * Saves the state of the application to disk.
     * Limited to writing tasks to disk, fails quietely for now.
     */
    public void saveState() {
        try {
            saveTasks();
        } catch (IOException e) {
            //fail quietly
        }
    }

    /**
     * Restores saved tasks if TASK_FILE_PATH exists, does nothing otherwise.
     * Assumes TASK_FILE_PATH is a properly formatted text file.
     */
    public void restoreTasks() {
        ArrayList<Task> tasks = taskManager.getTasks();
        File taskFile = new File(TASK_FILE_PATH);
        try {
            Scanner s = new Scanner(taskFile);
            while (s.hasNext()) {
                String[] savedTaskDetails = s.nextLine().split("\\|");
                Task savedTask = restoreTask(savedTaskDetails);
                tasks.add(savedTask);
            }
        } catch (FileNotFoundException e) {
            return;
        }
    }

    /**
     * Writes tasks in their text-parsable format to TASK_FILE_PATH.
     *
     * @throws IOException If fails to write tasks to disk.
     */
    public void saveTasks() throws IOException {
        ArrayList<Task> tasks = taskManager.getTasks();
        new File(DATA_DIR_PATH).mkdirs();
        File taskFile = new File(TASK_FILE_PATH);
        try {
            taskFile.createNewFile();
        } catch (IOException e) {
            throw e;
        }
        boolean isAppend = false;
        for (Task task : tasks) {
            try {
                writeTaskToFile(task, isAppend);
            } catch (IOException e) {
                throw e;
            }
            isAppend = true;
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

    private void writeTaskToFile(Task task, boolean isAppend) throws IOException {
        FileWriter fw = new FileWriter(TASK_FILE_PATH, isAppend);
        fw.write(task.toSaveTextFormat());
        fw.close();
    }
}
