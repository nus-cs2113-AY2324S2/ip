package Blue;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TaskManager extends Blue {
    public static final String DATA_DIR_PATH = "data";
    public static final String TASK_FILE_PATH = DATA_DIR_PATH + "/tasks.txt";
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int numTasks = 0;
    private Input request;

    public TaskManager() {
        restoreTasks();
    }

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
        case delete:
            deleteTask(request.getTaskIndex());
            break;
        case todo:
        case deadline:
        case event:
            addTask(request.getTaskToAdd(), true);
            break;
        default:
        }
        saveTasks();
    }

    private void listTasks() {
        int i = 0;
        for (Task task : tasks) {
            talk((i + 1) + ". " + task);
            i += 1;
        }
    }

    private void markTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= numTasks) {
            talk("Task not found.");
            return;
        }
        Task taskToMark = tasks.get(taskIndex);
        taskToMark.setDone();
        talk("Task " + taskToMark.getDescription() + " marked as done.");
    }

    private void deleteTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= numTasks) {
            talk("Task not found.");
            return;
        }
        talk("Task " + tasks.get(taskIndex).getDescription() + " deleted.");
        tasks.remove(taskIndex);
        numTasks -= 1;
    }

    private void addTask(Task task, boolean isNew) {
        tasks.add(task);
        numTasks += 1;
        if (isNew) {
            talk("added: " + task.getDescription());
        }
    }

    private void restoreTasks() {
        File taskFile = new File(TASK_FILE_PATH);
        try {
            Scanner s = new Scanner(taskFile);
            //assume taskFile is in the proper format for now
            while (s.hasNext()) {
                String[] savedTaskDetails = s.nextLine().split("\\|");
                Task savedTask = restoreTask(savedTaskDetails);
                addTask(savedTask, false);
            }
        } catch (FileNotFoundException e) {
            talk("No saved tasks found, starting from scratch.");
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
                writeTaskToFile(tasks.get(i), (i != 0));
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
