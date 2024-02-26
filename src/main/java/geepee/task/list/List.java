package geepee.task.list;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import geepee.task.Task;
import geepee.system.SystemMessage;
import geepee.system.FileHandler;

public class List {

    protected ArrayList<Task> tasks;
    protected FileHandler fileHandler;

    public List(String filePath) {
        try {
            fileHandler = new FileHandler(filePath);
            tasks = fileHandler.getTasksFromFile();
        } catch (FileNotFoundException e) {
            tasks = new ArrayList<>();
            SystemMessage.printFileNotFoundMessage();
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
        ListMessage.printAfterAddingTask(tasks.size(), task);
    }

    public void deleteTask(int index) {
        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        ListMessage.printAfterRemovingTask(tasks.size(), deletedTask);
    }

    public void changeTaskStatus(int index, boolean isDone) {
        tasks.get(index).changeStatus(isDone);
        ListMessage.printTaskStatusMessage(isDone, tasks.get(index));
    }

    public void findTasksFromKeyword(String keyword) {
        ArrayList<Task> relevantTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                relevantTasks.add(task);
            }
        }
        ListMessage.printRelevantTasks(relevantTasks);
    }

    public int getSize() {
        return tasks.size();
    }

    public void getAllTasks() {
        ListMessage.printAllTasks(tasks);
    }

    public void writeTasksToFile() {
        fileHandler.writeTasks(tasks);
    }
}
