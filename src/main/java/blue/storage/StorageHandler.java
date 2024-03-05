package blue.storage;

import blue.task.TaskManager;
import blue.task.Deadline;
import blue.task.Event;
import blue.task.Task;
import blue.exception.StorageException;

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
     * Limited to writing tasks to disk for now.
     */
    public void saveState() throws StorageException {
        try {
            saveTasks();
        } catch (StorageException e) {
            throw e;
        }
    }

    /**
     * Restores saved tasks if TASK_FILE_PATH exists, does nothing otherwise.
     * Assumes TASK_FILE_PATH is a properly formatted text file.
     */
    private void restoreTasks() {
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
        default:
            restoredTask = new Event(savedDetails[2], savedDetails[4], savedDetails[3]);
            break;
        }
        if (savedDetails[1].equals("1")) {
            restoredTask.setDone();
        }
        return restoredTask;
    }

    /**
     * Writes tasks in their text-parsable format to TASK_FILE_PATH.
     *
     * @throws StorageException If fails to write tasks to disk.
     */
    private void saveTasks() throws StorageException {
        ArrayList<Task> tasks = taskManager.getTasks();
        new File(DATA_DIR_PATH).mkdirs();
        File taskFile = new File(TASK_FILE_PATH);
        try {
            taskFile.createNewFile();
        } catch (IOException e) {
            throw new StorageException();
        }
        boolean isAppend = false;
        for (Task task : tasks) {
            try {
                writeTaskToFile(task, isAppend);
            } catch (IOException e) {
                throw new StorageException();
            }
            isAppend = true;
        }
    }

    private void writeTaskToFile(Task task, boolean isAppend) throws IOException {
        FileWriter fw = new FileWriter(TASK_FILE_PATH, isAppend);
        fw.write(task.toSaveTextFormat());
        fw.close();
    }
}
