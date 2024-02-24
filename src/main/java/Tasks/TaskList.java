package Tasks;

import FileManagerPackage.Storage;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    public ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<Task>();
    }
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
