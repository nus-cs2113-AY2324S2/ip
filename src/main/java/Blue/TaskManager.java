package Blue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TaskManager extends Blue {
    // assume no more than 100 tasks for now
    public static final String DATA_DIR_PATH = "data";
    public static final String TASK_FILE_PATH = DATA_DIR_PATH + "/tasks.txt";
    public static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int numTasks = 0;
    private Input request;

    public TaskManager(Input request) {
        this.request = request;
    }

    //@Override
    public void performRequest() {
        switch (request.getCommand()) {
        case list:
            listTasks();
            return;
        case mark:
            markTask(request.getTaskIndex());
            break;
        case todo:
        case deadline:
        case event:
            addTask(request.getTaskToAdd());
            break;
        default:
        }
        saveTasks();
    }

    private void listTasks() {
        for (int i = 0; i < numTasks; i += 1) {
            talk((i + 1) + ". " + tasks[i]);
        }
    }

    private void markTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= numTasks) {
            talk("Task not found.");
            return;
        }
        tasks[taskIndex].setDone();
        talk("Task " + tasks[taskIndex].getDescription() + " marked as done.");
    }

    private void addTask(Task task) {
        tasks[numTasks] = task;
        numTasks += 1;
        talk("added: " + task.getDescription());
    }

    private void saveTasks() {
        new File(DATA_DIR_PATH).mkdirs();
        File taskFile = new File(TASK_FILE_PATH);
        try {
            taskFile.createNewFile();
        } catch (IOException e) {
            talk("Failed to save tasks.");
            return;
        }
        for (int i = 0; i < numTasks; i += 1) {
            try {
                writeTaskToFile(tasks[i], (i != 0));
            } catch (IOException e) {
                talk("Failed to save tasks.");
                return;
            }
        }
    }

    private void writeTaskToFile(Task task, boolean isAppend) throws IOException {
        FileWriter fw = new FileWriter(TASK_FILE_PATH, isAppend);
        fw.write(task.toSaveTextFormat());
        fw.close();
    }
}
