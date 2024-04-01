package Tasks;

import FileManagerPackage.Storage;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The TaskList class represents a collection of tasks and provides operations to manipulate the tasks.
 */
public class TaskList {
    public ArrayList<Task> taskList;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    /**
     * Constructs a TaskList by reading data from the provided Scanner.
     *
     * @param s The Scanner object for reading from the file.
     */
    public TaskList(Scanner s) {
        taskList = new ArrayList<Task>();
        Storage.readFile(s, taskList);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void remove(int i) {
        taskList.remove(i);
    }

    public void markDone(int i) {
        taskList.get(i).setDone(true);
    }

    public void markNotDone(int i) {
        taskList.get(i).setDone(false);
    }

    public void printTask() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i).getStatusIcon());
        }
    }
}
